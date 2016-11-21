package org.strokova.booker.api.entity;

import org.strokova.booker.common.model.Guest;

/**
 * author: Veronika, 10/30/2016.
 */
public final class GuestEntityFactory {

    private GuestEntityFactory() {}

    public static GuestEntity create(Guest guest) {
        return new GuestEntity()
                .setId(guest.getId())
                .setName(guest.getName())
                .setPhone(guest.getPhone());
    }
}
