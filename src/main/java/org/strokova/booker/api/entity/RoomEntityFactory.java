package org.strokova.booker.api.entity;

import org.strokova.booker.common.model.Room;

/**
 * author: Veronika, 10/30/2016.
 */
public final class RoomEntityFactory {
    private RoomEntityFactory() {}

    public static RoomEntity create(Room room, HotelEntity hotelEntity) {
        return new RoomEntity()
                .setType(room.getType())
                .setHasTv(room.isHasTv())
                .setHasBalcony(room.isHasBalcony())
                .setHasAirConditioner(room.isHasAirConditioner())
                .setHasRubbishView(room.isHasRubbishView())
                .setHasPoolView(room.isHasPoolView())
                .setHasSeaView(room.isHasSeaView())
                .setHasFixedDateReservation(room.isHasFixedDateReservation())
                .setHotel(hotelEntity);
    }
}
