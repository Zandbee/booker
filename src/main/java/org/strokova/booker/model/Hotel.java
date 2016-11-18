package org.strokova.booker.model;


import org.strokova.booker.api.entity.HotelEntity;

import java.util.Objects;

/**
 * author: Veronika, 10/30/2016.
 */
public class Hotel {
    private Integer id;
    private String name;
    private boolean hasPool = false;
    private boolean hasWaterpark = false;
    private boolean hasTennisCourt = false;
    // TODO: on update, if boolean values are not provided, all turn to false (default is applied)

    public Hotel() {}

    /*public Hotel(Integer id, String name, boolean hasPool, boolean hasWaterpark, boolean hasTennisCourt) {
        this.id = id;
        this.name = name;
        this.hasPool = hasPool;
        this.hasWaterpark = hasWaterpark;
        this.hasTennisCourt = hasTennisCourt;
    }*/

    public Hotel (HotelEntity hotelEntity) {
        this.id = hotelEntity.getId();
        this.name = hotelEntity.getName();
        this.hasPool = hotelEntity.isHasPool();
        this.hasWaterpark = hotelEntity.isHasWaterpark();
        this.hasTennisCourt = hotelEntity.isHasTennisCourt();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return hasPool == hotel.hasPool &&
                hasWaterpark == hotel.hasWaterpark &&
                hasTennisCourt == hotel.hasTennisCourt &&
                Objects.equals(id, hotel.id) &&
                Objects.equals(name, hotel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hasPool, hasWaterpark, hasTennisCourt);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hasPool=" + hasPool +
                ", hasWaterpark=" + hasWaterpark +
                ", hasTennisCourt=" + hasTennisCourt +
                '}';
    }
}
