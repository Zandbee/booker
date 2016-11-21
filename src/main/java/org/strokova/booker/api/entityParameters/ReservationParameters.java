package org.strokova.booker.api.entityParameters;

import static org.strokova.booker.common.queryParameters.ReservationQueryParameters.*;

/**
 * 03.11.2016.
 */
public enum ReservationParameters {
    ID("id", RESERVATION_QUERY_PARAM_ID),
    DATE_FROM("dateFrom", RESERVATION_QUERY_PARAM_DATE_FROM),
    DATE_TO("dateTo", RESERVATION_QUERY_PARAM_DATE_TO),
    ROOM("room", RESERVATION_QUERY_PARAM_ROOM),
    GUEST("guest", RESERVATION_QUERY_PARAM_GUEST);

    // exact field name in class, corresponding to column in db
    private String columnName;
    // for clients to use in their sorting requests as parameter value and
    // query strings as parameter name
    private String queryParameterName;

    // private
    ReservationParameters(String columnName, String sortColumn) {
        this.columnName = columnName;
        this.queryParameterName = sortColumn;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getQueryParameterName() {
        return queryParameterName;
    }
}
