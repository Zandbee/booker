package org.strokova.booker.api.searchPredicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.strokova.booker.api.entity.QRoomEntity;

/**
 * 31.10.2016.
 */
public final class RoomSearchPredicates {
    private RoomSearchPredicates() {}

    //public static BooleanExpression typeIs()

    public static BooleanExpression hasTv() {
        return QRoomEntity.roomEntity.hasTv.isTrue();
    }

    public static BooleanExpression hasBalcony() {
        return QRoomEntity.roomEntity.hasBalcony.isTrue();
    }

    public static BooleanExpression hasAirConditioner() {
        return QRoomEntity.roomEntity.hasAirConditioner.isTrue();
    }

    public static BooleanExpression hasRubbishView() {
        return QRoomEntity.roomEntity.hasRubbishView.isTrue();
    }

    public static BooleanExpression hasPoolView() {
        return QRoomEntity.roomEntity.hasPoolView.isTrue();
    }

    public static BooleanExpression hasSeaView() {
        return QRoomEntity.roomEntity.hasSeaView.isTrue();
    }

    public static BooleanExpression hasFixedDateReservation() {
        return QRoomEntity.roomEntity.hasFixedDateReservation.isTrue();
    }

    // TODO: something for hotel, reservations
}
