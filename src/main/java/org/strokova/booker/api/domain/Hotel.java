package org.strokova.booker.api.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 28.10.2016.
 */
@Entity
public class Hotel {
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

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private Set<Room> rooms = new HashSet<>();

    public Hotel() {}

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

    public Hotel setId(Integer id) {
        this.id = id;
        return this;
    }

    public Hotel setName(String name) {
        this.name = name;
        return this;
    }

    public Hotel setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
        return this;
    }

    public Hotel setHasWaterpark(boolean hasWaterpark) {
        this.hasWaterpark = hasWaterpark;
        return this;
    }

    public Hotel setHasTennisCourt(boolean hasTennisCourt) {
        this.hasTennisCourt = hasTennisCourt;
        return this;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public Hotel setRooms(Set<Room> rooms) {
        this.rooms = rooms;
        return this;
    }
}
