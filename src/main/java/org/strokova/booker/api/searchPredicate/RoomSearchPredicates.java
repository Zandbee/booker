package org.strokova.booker.api.searchPredicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.strokova.booker.api.entity.QRoomEntity;

/**
 * 31.10.2016.
 */
public final class RoomSearchPredicates {
    private RoomSearchPredicates() {}

    //public static BooleanExpression typeIs()

    public static BooleanExpression hasTv(Boolean value) {
        return QRoomEntity.roomEntity.hasTv.eq(value);
    }

    public static BooleanExpression hasBalcony(Boolean value) {
        return QRoomEntity.roomEntity.hasBalcony.eq(value);
    }

    public static BooleanExpression hasAirConditioner(Boolean value) {
        return QRoomEntity.roomEntity.hasAirConditioner.eq(value);
    }

    public static BooleanExpression hasRubbishView(Boolean value) {
        return QRoomEntity.roomEntity.hasRubbishView.eq(value);
    }

    public static BooleanExpression hasPoolView(Boolean value) {
        return QRoomEntity.roomEntity.hasPoolView.eq(value);
    }

    public static BooleanExpression hasSeaView(Boolean value) {
        return QRoomEntity.roomEntity.hasSeaView.eq(value);
    }

    public static BooleanExpression hasFixedDateReservation(Boolean value) {
        return QRoomEntity.roomEntity.hasFixedDateReservation.eq(value);
    }

    // TODO: something for hotel, reservations
}
