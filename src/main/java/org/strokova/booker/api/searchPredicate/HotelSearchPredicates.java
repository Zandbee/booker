package org.strokova.booker.api.searchPredicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.strokova.booker.api.entity.QHotelEntity;

/**
 * 31.10.2016.
 */
public final class HotelSearchPredicates {
    private HotelSearchPredicates() {}

    public static BooleanExpression hasPool() {
        return QHotelEntity.hotelEntity.hasPool.isTrue();
    }

    public static BooleanExpression hasWaterpark() {
        return QHotelEntity.hotelEntity.hasWaterpark.isTrue();
    }

    public static BooleanExpression hasTennisCourt() {
        return QHotelEntity.hotelEntity.hasTennisCourt.isTrue();
    }

    // TODO: need something for rooms?
}
