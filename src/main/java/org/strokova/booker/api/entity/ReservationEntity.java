package org.strokova.booker.api.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 28.10.2016.
 */
@Entity
@Table(name = "reservation")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: must be not empty?
    private Date dateFrom;
    private Date dateTo;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private GuestEntity guest;

    public Long getId() {
        return id;
    }

    public ReservationEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public ReservationEntity setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public ReservationEntity setDateTo(Date dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public ReservationEntity setRoom(RoomEntity room) {
        this.room = room;
        return this;
    }

    public GuestEntity getGuest() {
        return guest;
    }

    public ReservationEntity setGuest(GuestEntity guest) {
        this.guest = guest;
        return this;
    }
}
