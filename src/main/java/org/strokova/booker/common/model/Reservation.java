package org.strokova.booker.common.model;

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

    public Reservation() {}

    /*public Reservation(Long id, Date dateFrom, Date dateTo) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }*/

    public Reservation(ReservationEntity reservationEntity) {
        this.id = reservationEntity.getId();
        this.dateFrom = reservationEntity.getDateFrom();
        this.dateTo = reservationEntity.getDateTo();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dateFrom, that.dateFrom) &&
                Objects.equals(dateTo, that.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateFrom, dateTo);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                "}";
    }
}
