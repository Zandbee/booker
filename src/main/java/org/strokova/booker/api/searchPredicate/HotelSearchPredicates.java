package org.strokova.booker.api.searchPredicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.strokova.booker.api.entity.QHotelEntity;

/**
 * 31.10.2016.
 */
public final class HotelSearchPredicates {
    private HotelSearchPredicates() {}

    public static BooleanExpression hasPool(Boolean value) {
        return QHotelEntity.hotelEntity.hasPool.eq(value);
    }

    public static BooleanExpression hasWaterpark(Boolean value) {
        return QHotelEntity.hotelEntity.hasWaterpark.eq(value);
    }

    public static BooleanExpression hasTennisCourt(Boolean value) {
        return QHotelEntity.hotelEntity.hasTennisCourt.eq(value);
    }

    public static BooleanExpression nameIn(String... value) {
        return QHotelEntity.hotelEntity.name.in(value);
    }
}
