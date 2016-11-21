package org.strokova.booker.api.searchPredicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.strokova.booker.api.entity.QRoomEntity;
import org.strokova.booker.common.model.RoomType;

/**
 * 31.10.2016.
 */
public final class RoomSearchPredicates {
    private RoomSearchPredicates() {}

    public static BooleanExpression typeIs(RoomType value) {
        return QRoomEntity.roomEntity.type.eq(value);
    }

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

    public static BooleanExpression hotelIs(Integer hotelId) {
        return QRoomEntity.roomEntity.hotel.id.eq(hotelId);
    }
}
