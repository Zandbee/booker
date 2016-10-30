package org.strokova.booker.api.entity;

import org.strokova.booker.api.model.Hotel;

/**
 * author: Veronika, 10/30/2016.
 */
public final class HotelEntityFactory {

    private HotelEntityFactory() {}

    public static HotelEntity create(Hotel hotel) {
        return new HotelEntity()
                .setId(hotel.getId())
                .setName(hotel.getName())
                .setHasPool(hotel.isHasPool())
                .setHasWaterpark(hotel.isHasWaterpark())
                .setHasTennisCourt(hotel.isHasTennisCourt());
    }
}
