package org.strokova.booker.api.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 28.10.2016.
 */
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateFrom;
    private Date dateTo;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Guest guest;

    public Long getId() {
        return id;
    }

    public Reservation setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Reservation setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public Reservation setDateTo(Date dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public Reservation setRoom(Room room) {
        this.room = room;
        return this;
    }

    public Guest getGuest() {
        return guest;
    }

    public Reservation setGuest(Guest guest) {
        this.guest = guest;
        return this;
    }
}
