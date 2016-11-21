package org.strokova.booker.common.model;

import org.strokova.booker.api.entity.RoomEntity;

import java.util.Objects;

/**
 * author: Veronika, 10/30/2016.
 */
public class Room {
    private Long id;
    private RoomType type;
    private boolean hasTv = false;
    private boolean hasBalcony = false;
    private boolean hasAirConditioner = false;
    private boolean hasRubbishView = false;
    private boolean hasPoolView = false;
    private boolean hasSeaView = false;
    private boolean hasFixedDateReservation = false;

    public Room() {}

    /*public Room(Long id, RoomType type, boolean hasTv, boolean hasBalcony, boolean hasAirConditioner, boolean hasRubbishView, boolean hasPoolView, boolean hasSeaView, boolean hasFixedDateReservation) {
        this.id = id;
        this.type = type;
        this.hasTv = hasTv;
        this.hasBalcony = hasBalcony;
        this.hasAirConditioner = hasAirConditioner;
        this.hasRubbishView = hasRubbishView;
        this.hasPoolView = hasPoolView;
        this.hasSeaView = hasSeaView;
        this.hasFixedDateReservation = hasFixedDateReservation;
    }*/

    public Room (RoomEntity roomEntity) {
        this.id = roomEntity.getId();
        this.type = roomEntity.getType();
        this.hasTv = roomEntity.isHasTv();
        this.hasBalcony = roomEntity.isHasBalcony();
        this.hasAirConditioner = roomEntity.isHasAirConditioner();
        this.hasRubbishView = roomEntity.isHasRubbishView();
        this.hasPoolView = roomEntity.isHasPoolView();
        this.hasSeaView = roomEntity.isHasSeaView();
        this.hasFixedDateReservation = roomEntity.isHasFixedDateReservation();
    }

    public Long getId() {
        return id;
    }

    public RoomType getType() {
        return type;
    }

    public boolean isHasTv() {
        return hasTv;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public boolean isHasAirConditioner() {
        return hasAirConditioner;
    }

    public boolean isHasRubbishView() {
        return hasRubbishView;
    }

    public boolean isHasPoolView() {
        return hasPoolView;
    }

    public boolean isHasSeaView() {
        return hasSeaView;
    }

    public boolean isHasFixedDateReservation() {
        return hasFixedDateReservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return hasTv == room.hasTv &&
                hasBalcony == room.hasBalcony &&
                hasAirConditioner == room.hasAirConditioner &&
                hasRubbishView == room.hasRubbishView &&
                hasPoolView == room.hasPoolView &&
                hasSeaView == room.hasSeaView &&
                hasFixedDateReservation == room.hasFixedDateReservation &&
                Objects.equals(id, room.id) &&
                type == room.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, hasTv, hasBalcony, hasAirConditioner, hasRubbishView, hasPoolView, hasSeaView, hasFixedDateReservation);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", type=" + type +
                ", hasTv=" + hasTv +
                ", hasBalcony=" + hasBalcony +
                ", hasAirConditioner=" + hasAirConditioner +
                ", hasRubbishView=" + hasRubbishView +
                ", hasPoolView=" + hasPoolView +
                ", hasSeaView=" + hasSeaView +
                ", hasFixedDateReservation=" + hasFixedDateReservation +
                '}';
    }
}
