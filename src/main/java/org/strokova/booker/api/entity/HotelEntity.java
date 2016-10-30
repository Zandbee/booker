package org.strokova.booker.api.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 28.10.2016.
 */
@Entity
@Table(name = "hotel")
public class HotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 200)
    private String name;

    @Column(name = "has_pool")
    private boolean hasPool = false;

    @Column(name = "has_waterpark")
    private boolean hasWaterpark = false;

    @Column(name = "has_tennis_court")
    private boolean hasTennisCourt = false;

    @OneToMany(mappedBy = "hotel")
    private Set<RoomEntity> rooms = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    public boolean isHasWaterpark() {
        return hasWaterpark;
    }

    public boolean isHasTennisCourt() {
        return hasTennisCourt;
    }

    public HotelEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public HotelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public HotelEntity setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
        return this;
    }

    public HotelEntity setHasWaterpark(boolean hasWaterpark) {
        this.hasWaterpark = hasWaterpark;
        return this;
    }

    public HotelEntity setHasTennisCourt(boolean hasTennisCourt) {
        this.hasTennisCourt = hasTennisCourt;
        return this;
    }

    public Set<RoomEntity> getRooms() {
        return rooms;
    }

    public HotelEntity setRooms(Set<RoomEntity> rooms) {
        this.rooms = rooms;
        return this;
    }
}
