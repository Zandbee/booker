package org.strokova.booker.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.strokova.booker.api.entity.GuestEntity;
import org.strokova.booker.api.entity.ReservationEntityFactory;
import org.strokova.booker.api.entity.RoomEntity;
import org.strokova.booker.api.model.GuestReservation;
import org.strokova.booker.api.model.Reservation;
import org.strokova.booker.api.repository.GuestRepository;
import org.strokova.booker.api.repository.ReservationRepository;
import org.strokova.booker.api.repository.RoomRepository;

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
}
