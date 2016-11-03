package org.strokova.booker.api.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.strokova.booker.api.entity.GuestEntity;
import org.strokova.booker.api.entity.ReservationEntityFactory;
import org.strokova.booker.api.entity.RoomEntity;
import org.strokova.booker.api.model.GuestReservation;
import org.strokova.booker.api.model.Reservation;
import org.strokova.booker.api.queryParameters.ReservationParameter;
import org.strokova.booker.api.repository.GuestRepository;
import org.strokova.booker.api.repository.ReservationRepository;
import org.strokova.booker.api.repository.RoomRepository;
import org.strokova.booker.api.searchPredicate.ReservationSearchPredicates;

import java.util.Date;

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
        RoomEntity roomEntity = roomRepository.findByIdAndHotelId(roomId, hotelId);
        if (roomEntity == null) {
            throw new IllegalArgumentException("Cannot find room with roomId = " + roomId + " and hotelId = " + hotelId);
        }

        Long guestId = guestReservation.getGuestId();
        GuestEntity guestEntity = guestRepository.findOne(guestId);
        if (guestEntity == null) {
            throw new IllegalArgumentException("Cannot find guest with id = " + guestId);
        }

        Reservation reservation = guestReservation.getReservation();
        if (reservation.getDateFrom() == null || reservation.getDateTo() == null) {
            throw new IllegalArgumentException("Reservation dates cannot be empty");
        }
        // TODO: check if this room is free for these dates
        // TODO: fixed reservations!

        return new Reservation(reservationRepository.save(ReservationEntityFactory.create(reservation, guestEntity, roomEntity)));
    }

    @Transactional(readOnly = true)
    public Page<Reservation> findReservations(Integer hotelId, Long roomId,
                                              Integer page, Integer size, String order, String by,
                                              Date dateFrom, Date dateTo, Long guestId) {
        if (roomRepository.findByIdAndHotelId(roomId, hotelId) == null) {
            throw new IllegalArgumentException("Cannot find room with roomId = " + roomId + " and hotelId = " + hotelId);
        }

        return reservationRepository.findAll(
                createSearchPredicate(roomId, dateFrom, dateTo, guestId),
                createPageRequest(page, size, order, by))
                .map(Reservation::new);
    }

    private BooleanBuilder createSearchPredicate(Long roomId, Date dateFrom, Date dateTo, Long guestId) {
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

    private PageRequest createPageRequest(Integer page, Integer size, String order, String by) {
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
