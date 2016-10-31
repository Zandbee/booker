package org.strokova.booker.api.searchPredicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.strokova.booker.api.entity.QReservationEntity;

import java.util.Date;

/**
 * 31.10.2016.
 */
public final class ReservationSearchPredicates {
    private ReservationSearchPredicates() {}

    public static BooleanExpression dateFromIs(Date datePredicate) {
        return QReservationEntity.reservationEntity.dateFrom.eq(datePredicate);
    }

    public static BooleanExpression dateFromIsBefore(Date datePredicate) {
        return QReservationEntity.reservationEntity.dateFrom.before(datePredicate);
    }

    public static BooleanExpression dateFromIsAfter(Date datePredicate) {
        return QReservationEntity.reservationEntity.dateFrom.after(datePredicate);
    }

    public static BooleanExpression dateFromIsAfter(Date fromPredicate, Date toPredicate) {
        return QReservationEntity.reservationEntity.dateFrom.between(fromPredicate, toPredicate);
    }

    public static BooleanExpression dateToIs(Date datePredicate) {
        return QReservationEntity.reservationEntity.dateTo.eq(datePredicate);
    }

    public static BooleanExpression dateToIsBefore(Date datePredicate) {
        return QReservationEntity.reservationEntity.dateTo.before(datePredicate);
    }

    public static BooleanExpression dateToIsAfter(Date datePredicate) {
        return QReservationEntity.reservationEntity.dateTo.after(datePredicate);
    }

    public static BooleanExpression dateToIsAfter(Date fromPredicate, Date toPredicate) {
        return QReservationEntity.reservationEntity.dateTo.between(fromPredicate, toPredicate);
    }

    // TODO: something for guest, room
}
