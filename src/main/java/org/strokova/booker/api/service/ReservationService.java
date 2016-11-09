package org.strokova.booker.api.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.strokova.booker.api.entity.GuestEntity;
import org.strokova.booker.api.entity.ReservationEntity;
import org.strokova.booker.api.entity.ReservationEntityFactory;
import org.strokova.booker.api.entity.RoomEntity;
import org.strokova.booker.api.model.GuestReservation;
import org.strokova.booker.api.model.Reservation;
import org.strokova.booker.api.queryParameters.ReservationParameter;
import org.strokova.booker.api.repository.GuestRepository;
import org.strokova.booker.api.repository.ReservationRepository;
import org.strokova.booker.api.repository.RoomRepository;

import java.util.Date;
import java.util.List;

import static org.strokova.booker.api.searchPredicate.ReservationSearchPredicates.*;

/**
 * 03.11.2016.
 */
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository, GuestRepository guestRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
    }

    @Transactional
    public Reservation save(GuestReservation guestReservation, Integer hotelId, Long roomId) {
        checkIfRoomExists(hotelId, roomId);

        Reservation reservation = guestReservation.getReservation();
        Date dateFrom = reservation.getDateFrom();
        Date dateTo = reservation.getDateTo();
        if (dateFrom == null || dateTo == null) {
            throw new IllegalArgumentException("Reservation dates cannot be empty");
        }

        RoomEntity roomEntity = roomRepository.findByIdAndHotelId(roomId, hotelId);

        // check if this room is free for these dates
        checkIfRoomIsEmpty(roomEntity, dateFrom, dateTo);

        Long guestId = guestReservation.getGuestId();
        GuestEntity guestEntity = guestRepository.findOne(guestId);
        if (guestEntity == null) {
            throw new IllegalArgumentException("Cannot find guest with id = " + guestId);
        }

        // TODO: fixed reservations!

        return new Reservation(reservationRepository.save(ReservationEntityFactory.create(reservation, guestEntity, roomEntity)));
    }

    @Transactional(readOnly = true)
    public Page<Reservation> findReservations(Integer hotelId, Long roomId,
                                              Integer page, Integer size, String order, String by,
                                              Date dateFrom, Date dateTo, Long guestId) {
        checkIfRoomExists(hotelId, roomId);

        return reservationRepository.findAll(
                createSearchPredicate(roomId, dateFrom, dateTo, guestId),
                createPageRequest(page, size, order, by))
                .map(Reservation::new);
    }

    @Transactional(readOnly = true)
    public Reservation findReservation(Integer hotelId, Long roomId, Long reservationId) {
        if (roomRepository.findByIdAndHotelId(roomId, hotelId) == null) {
            throw new IllegalArgumentException("Cannot find room with roomId = " + roomId + " and hotelId = " + hotelId);
        }

        return new Reservation(reservationRepository.findByIdAndRoomId(reservationId, roomId));
    }

    @Transactional
    public void deleteReservation(Integer hotelId, Long roomId, Long reservationId) {
        checkIfRoomExists(hotelId, roomId);
        reservationRepository.deleteByIdAndRoomId(reservationId, roomId);
    }

    @Transactional
    public Reservation updateReservation(GuestReservation newReservationData, Integer hotelId, Long roomId, Long reservationId) {
        Reservation newReservation = newReservationData.getReservation();
        if (newReservation != null) {
            Long newId = newReservation.getId();
            if (newId != null && !newId.equals(reservationId)) {
                throw new IllegalArgumentException("Impossible to update reservation id");
            }
        }

        checkIfRoomExists(hotelId, roomId);

        ReservationEntity oldReservationEntity = reservationRepository.findByIdAndRoomId(reservationId, roomId);
        if (oldReservationEntity == null) {
            throw new IllegalArgumentException("Cannot find reservation with id = " + reservationId + " for roomId = " + roomId);
        }

        // check if the dates do not intersect with other reservations for this room, except this reservationId
        // TODO: уже проверяли, что комната существует, а тут опять ее запрашиваем - криво
        RoomEntity roomEntity = roomRepository.findByIdAndHotelId(roomId, hotelId);
        checkIfRoomIsEmptyExceptThisReservation(roomEntity, oldReservationEntity, newReservation);

        return new Reservation(reservationRepository.save(updateReservationData(oldReservationEntity, newReservationData)));
    }

    @Transactional(readOnly = true)
    private void checkIfRoomExists(Integer hotelId, Long roomId) {
        if (roomRepository.findByIdAndHotelId(roomId, hotelId) == null) {
            throw new IllegalArgumentException("Cannot find room with roomId = " + roomId + " and hotelId = " + hotelId);
        }
    }

    // TODO: return bool?
    @Transactional(readOnly = true)
    private void checkIfRoomIsEmpty(RoomEntity room, Date dateFrom, Date dateTo) {
        List<ReservationEntity> existingReservations = reservationRepository.findDatesIntersection(room, dateFrom, dateTo);
        if (!existingReservations.isEmpty()) {
            throw new IllegalArgumentException("This room is already booked for these dates");
        }
    }

    // TODO: return bool?
    @Transactional(readOnly = true)
    private void checkIfRoomIsEmptyExceptThisReservation(
            RoomEntity room, ReservationEntity existingReservationEntity, Reservation newReservation) {
        if (newReservation == null) {
            return;
        }

        Long reservationId = existingReservationEntity.getId();

        Date newDateFrom = newReservation.getDateFrom();
        Date newDateTo = newReservation.getDateTo();

        if (newDateFrom == null) {
            newDateFrom = existingReservationEntity.getDateFrom();
        }
        if (newDateTo == null) {
            newDateTo = existingReservationEntity.getDateTo();
        }

        List<ReservationEntity> existingReservations = reservationRepository
                .findDatesIntersectionExceptThisReservation(room, newDateFrom, newDateTo, reservationId);
        if (!existingReservations.isEmpty()) {
            throw new IllegalArgumentException("This room is already booked for these dates");
        }
    }

    private ReservationEntity updateReservationData(ReservationEntity reservationEntity, GuestReservation data) {
        Reservation newReservation = data.getReservation();
        if (newReservation != null) {
            Date newDateFrom = newReservation.getDateFrom();
            Date newDateTo = newReservation.getDateTo();
            if (newDateFrom != null) {
                reservationEntity.setDateFrom(newDateFrom);
            }
            if (newDateTo != null) {
                reservationEntity.setDateTo(newDateTo);
            }
        }

        Long newGuestId = data.getGuestId();
        if (newGuestId != null) {
            reservationEntity.setGuest(guestRepository.findOne(newGuestId));
        }

        return reservationEntity;
    }

    private static BooleanBuilder createSearchPredicate(Long roomId, Date dateFrom, Date dateTo, Long guestId) {
        if (roomId == null) {
            throw new IllegalArgumentException("RoomId must not be null");
        }

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(roomIs(roomId));

        if (dateFrom != null && dateTo != null) {
            predicate.and(dateFromIsBetween(dateFrom, dateTo)).and(dateToIsBetween(dateFrom, dateTo));
        } else {
            if (dateFrom != null) {
                predicate.and(dateFromGoe(dateFrom));
            }
            if (dateTo != null) {
                predicate.and(dateToLoe(dateTo));
            }
        }

        if (guestId != null) {
            predicate.and(guestIs(guestId));
        }

        return predicate;
    }

    private static PageRequest createPageRequest(Integer page, Integer size, String order, String by) {
        return new PageRequest(page, size, ServiceUtils.determineSortDirection(order), determineSortProperty(by));
    }

    private static String determineSortProperty(String by) {
        String sortProperty = null;

        for (ReservationParameter param : ReservationParameter.values()) {
            if (by.equalsIgnoreCase(param.getQueryParameterName())) {
                sortProperty = param.getColumnName();
            }
        }
        // if by param is invalid - order by room
        if (sortProperty == null) {
            sortProperty = ReservationParameter.ROOM.getColumnName();
        }

        return sortProperty;
    }
}
