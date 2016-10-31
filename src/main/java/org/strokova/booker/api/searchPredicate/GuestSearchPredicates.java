package org.strokova.booker.api.searchPredicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.strokova.booker.api.entity.QGuestEntity;

/**
 * 31.10.2016.
 */
public final class GuestSearchPredicates {
    private GuestSearchPredicates() {}

    public static BooleanExpression nameIs(String namePredicate) {
        return QGuestEntity.guestEntity.name.equalsIgnoreCase(namePredicate);
    }
    // TODO: nameIn(String... names)?

    public static BooleanExpression phoneIs(String phonePredicate) {
        return QGuestEntity.guestEntity.phone.equalsIgnoreCase(phonePredicate);
    }

    // TODO: need something for reservations?
}
