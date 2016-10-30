package org.strokova.booker.api.entity;

import org.strokova.booker.api.model.Reservation;

/**
 * author: Veronika, 10/30/2016.
 */
public final class ReservationEntityFactory {
    private ReservationEntityFactory() {}

    public static ReservationEntity create(Reservation reservation) {
        return new ReservationEntity()
                .setId(reservation.getId())
                .setDateFrom(reservation.getDateFrom())
                .setDateTo(reservation.getDateTo())
                .setGuest(GuestEntityFactory.create(reservation.getGuest()))
                .setRoom(RoomEntityFactory.create(reservation.getRoom()));
    }
}
