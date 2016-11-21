package org.strokova.booker.api.entity;

import org.strokova.booker.common.model.Reservation;

/**
 * author: Veronika, 10/30/2016.
 */
public final class ReservationEntityFactory {
    private ReservationEntityFactory() {}

    public static ReservationEntity create(Reservation reservation, GuestEntity guestEntity, RoomEntity roomEntity) {
        return new ReservationEntity()
                .setId(reservation.getId())
                .setDateFrom(reservation.getDateFrom())
                .setDateTo(reservation.getDateTo())
                .setGuest(guestEntity)
                .setRoom(roomEntity);
    }
}
