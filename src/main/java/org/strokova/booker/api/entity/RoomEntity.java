package org.strokova.booker.api.entity;

import org.strokova.booker.common.model.RoomType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 28.10.2016.
 */
@Entity
@Table(name = "room")
public class RoomEntity {
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
    private HotelEntity hotel;

    @OneToMany(mappedBy = "room")
    private Set<ReservationEntity> reservations = new HashSet<>();

    public Long getId() {
        return id;
    }

    public RoomEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public RoomType getType() {
        return type;
    }

    public RoomEntity setType(RoomType type) {
        this.type = type;
        return this;
    }

    public boolean isHasTv() {
        return hasTv;
    }

    public RoomEntity setHasTv(boolean hasTv) {
        this.hasTv = hasTv;
        return this;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public RoomEntity setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
        return this;
    }

    public boolean isHasAirConditioner() {
        return hasAirConditioner;
    }

    public RoomEntity setHasAirConditioner(boolean hasAirConditioner) {
        this.hasAirConditioner = hasAirConditioner;
        return this;
    }

    public boolean isHasRubbishView() {
        return hasRubbishView;
    }

    public RoomEntity setHasRubbishView(boolean hasRubbishView) {
        this.hasRubbishView = hasRubbishView;
        return this;
    }

    public boolean isHasPoolView() {
        return hasPoolView;
    }

    public RoomEntity setHasPoolView(boolean hasPoolView) {
        this.hasPoolView = hasPoolView;
        return this;
    }

    public boolean isHasSeaView() {
        return hasSeaView;
    }

    public RoomEntity setHasSeaView(boolean hasSeaView) {
        this.hasSeaView = hasSeaView;
        return this;
    }

    public boolean isHasFixedDateReservation() {
        return hasFixedDateReservation;
    }

    public RoomEntity setHasFixedDateReservation(boolean hasFixedDateReservation) {
        this.hasFixedDateReservation = hasFixedDateReservation;
        return this;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public RoomEntity setHotel(HotelEntity hotel) {
        this.hotel = hotel;
        return this;
    }

    public Set<ReservationEntity> getReservations() {
        return reservations;
    }

    public RoomEntity setReservations(Set<ReservationEntity> reservations) {
        this.reservations = reservations;
        return this;
    }
}
