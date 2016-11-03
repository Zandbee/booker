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

    public static BooleanExpression dateFromLoe(Date value) {
        return QReservationEntity.reservationEntity.dateFrom.loe(value);
    }

    public static BooleanExpression dateFromGoe(Date value) {
        return QReservationEntity.reservationEntity.dateFrom.goe(value);
    }

    public static BooleanExpression dateFromIsBetween(Date valueFrom, Date valueTo) {
        return QReservationEntity.reservationEntity.dateFrom.between(valueFrom, valueTo);
    }

    public static BooleanExpression dateToIs(Date value) {
        return QReservationEntity.reservationEntity.dateTo.eq(value);
    }

    public static BooleanExpression dateToLoe(Date value) {
        return QReservationEntity.reservationEntity.dateTo.loe(value);
    }

    public static BooleanExpression dateToGoe(Date value) {
        return QReservationEntity.reservationEntity.dateTo.goe(value);
    }

    public static BooleanExpression dateToIsBetween(Date valueFrom, Date valueTo) {
        return QReservationEntity.reservationEntity.dateTo.between(valueFrom, valueTo);
    }

    public static BooleanExpression roomIs(Long roomId) {
        return QReservationEntity.reservationEntity.room.id.eq(roomId);
    }

    public static BooleanExpression guestIs(Long guestId) {
        return QReservationEntity.reservationEntity.guest.id.eq(guestId);
    }
}
