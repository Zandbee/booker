package org.strokova.booker.api.searchPredicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.strokova.booker.api.entity.QReservationEntity;

import java.util.Date;

/**
 * 31.10.2016.
 */
public final class ReservationSearchPredicates {
    private ReservationSearchPredicates() {}

    public static BooleanExpression dateFromIs(Date value) {
        return QReservationEntity.reservationEntity.dateFrom.eq(value);
    }

    public static BooleanExpression dateFromIsBefore(Date value) {
        return QReservationEntity.reservationEntity.dateFrom.before(value);
    }

    public static BooleanExpression dateFromIsAfter(Date value) {
        return QReservationEntity.reservationEntity.dateFrom.after(value);
    }

    public static BooleanExpression dateFromIsAfter(Date valueFrom, Date valueTo) {
        return QReservationEntity.reservationEntity.dateFrom.between(valueFrom, valueTo);
    }

    public static BooleanExpression dateToIs(Date value) {
        return QReservationEntity.reservationEntity.dateTo.eq(value);
    }

    public static BooleanExpression dateToIsBefore(Date value) {
        return QReservationEntity.reservationEntity.dateTo.before(value);
    }

    public static BooleanExpression dateToIsAfter(Date value) {
        return QReservationEntity.reservationEntity.dateTo.after(value);
    }

    public static BooleanExpression dateToIsAfter(Date valueFrom, Date valueTo) {
        return QReservationEntity.reservationEntity.dateTo.between(valueFrom, valueTo);
    }

    // TODO: something for guest, room
}
