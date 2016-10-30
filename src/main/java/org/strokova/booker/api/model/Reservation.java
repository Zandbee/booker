package org.strokova.booker.api.model;

import org.strokova.booker.api.entity.ReservationEntity;

import java.util.Date;
import java.util.Objects;

/**
 * author: Veronika, 10/30/2016.
 */
public class Reservation {
    private Long id;
    private Date dateFrom;
    private Date dateTo;
    private Room room;
    private Guest guest;

    public Reservation(Long id, Date dateFrom, Date dateTo, Room room, Guest guest) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.room = room;
        this.guest = guest;
    }

    public Reservation(ReservationEntity reservationEntity) {
        this.id = reservationEntity.getId();
        this.dateFrom = reservationEntity.getDateFrom();
        this.dateTo = reservationEntity.getDateTo();
        this.room = new Room(reservationEntity.getRoom());
        this.guest = new Guest(reservationEntity.getGuest());
    }

    public Long getId() {
        return id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public Room getRoom() {
        return room;
    }

    public Guest getGuest() {
        return guest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dateFrom, that.dateFrom) &&
                Objects.equals(dateTo, that.dateTo) &&
                Objects.equals(room, that.room) &&
                Objects.equals(guest, that.guest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateFrom, dateTo, room, guest);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", room=" + room +
                ", guest=" + guest +
                '}';
    }
}
