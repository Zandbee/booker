package org.strokova.booker.api.entityParameters;

import static org.strokova.booker.common.queryParameters.HotelQueryParameters.*;

/**
 * 01.11.2016.
 */
public enum HotelParameters {
    ID("id", HOTEL_QUERY_PARAM_ID),
    NAME("name", HOTEL_QUERY_PARAM_NAME),
    HAS_POOL("hasPool", HOTEL_QUERY_PARAM_HAS_POOL),
    HAS_WATERPARK("hasWaterpark", HOTEL_QUERY_PARAM_HAS_WATERPARK),
    HAS_TENNIS_COURT("hasTennisCourt", HOTEL_QUERY_PARAM_HAS_TENNIS_COURT);

    // exact field name in class, corresponding to column in db
    private String columnName;
    // for clients to use in their sorting requests as parameter value and
    // query strings as parameter name
    private String queryParameterName;

    // private
    HotelParameters(String columnName, String sortColumn) {
        this.columnName = columnName;
        this.queryParameterName = sortColumn;
    }

    public String getQueryParameterName() {
        return queryParameterName;
    }

    public String getColumnName() {
        return columnName;
    }
}
