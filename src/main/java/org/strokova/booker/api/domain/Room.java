package org.strokova.booker.api.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 28.10.2016.
 */
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Column(name = "has_tv")
    private boolean hasTv = false;

    @Column(name = "has_balcony")
    private boolean hasBalcony = false;

    @Column(name = "has_air_conditioner")
    private boolean hasAirConditioner = false;

    @Column(name = "has_rubbish_view")
    private boolean hasRubbishView = false;

    @Column(name = "has_pool_view")
    private boolean hasPoolView = false;

    @Column(name = "has_sea_view")
    private boolean hasSeaView = false;

    @Column(name = "has_fixed_date_reservation")
    private boolean hasFixedDateReservation = false;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private Set<Reservation> reservations = new HashSet<>();

    public Room() {}

    public Long getId() {
        return id;
    }

    public Room setId(Long id) {
        this.id = id;
        return this;
    }

    public RoomType getType() {
        return type;
    }

    public Room setType(RoomType type) {
        this.type = type;
        return this;
    }

    public boolean isHasTv() {
        return hasTv;
    }

    public Room setHasTv(boolean hasTv) {
        this.hasTv = hasTv;
        return this;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public Room setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
        return this;
    }

    public boolean isHasAirConditioner() {
        return hasAirConditioner;
    }

    public Room setHasAirConditioner(boolean hasAirConditioner) {
        this.hasAirConditioner = hasAirConditioner;
        return this;
    }

    public boolean isHasRubbishView() {
        return hasRubbishView;
    }

    public Room setHasRubbishView(boolean hasRubbishView) {
        this.hasRubbishView = hasRubbishView;
        return this;
    }

    public boolean isHasPoolView() {
        return hasPoolView;
    }

    public Room setHasPoolView(boolean hasPoolView) {
        this.hasPoolView = hasPoolView;
        return this;
    }

    public boolean isHasSeaView() {
        return hasSeaView;
    }

    public Room setHasSeaView(boolean hasSeaView) {
        this.hasSeaView = hasSeaView;
        return this;
    }

    public boolean isHasFixedDateReservation() {
        return hasFixedDateReservation;
    }

    public Room setHasFixedDateReservation(boolean hasFixedDateReservation) {
        this.hasFixedDateReservation = hasFixedDateReservation;
        return this;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Room setHotel(Hotel hotel) {
        this.hotel = hotel;
        return this;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public Room setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
        return this;
    }
}
