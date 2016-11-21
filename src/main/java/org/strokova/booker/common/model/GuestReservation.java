package org.strokova.booker.common.model;

/**
 * 03.11.2016.
 */
public class GuestReservation {
    private Reservation reservation;
    private Long guestId;

    public GuestReservation() {}

    /*public GuestReservation(Reservation reservation, Long guestId) {
        this.reservation = reservation;
        this.guestId = guestId;
    }*/

    public Reservation getReservation() {
        return reservation;
    }

    public Long getGuestId() {
        return guestId;
    }

    // TODO: need equals, toString, hashCode?
}
