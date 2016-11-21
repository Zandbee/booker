package org.strokova.booker.api.entityParameters;

import static org.strokova.booker.common.queryParameters.GuestQueryParameters.*;

/**
 * 02.11.2016.
 */
public enum GuestParameters {
    ID("id", GUEST_QUERY_PARAM_ID),
    NAME("name", GUEST_QUERY_PARAM_NAME),
    PHONE("phone", GUEST_QUERY_PARAM_PHONE);

    // exact field name in class, corresponding to column in db
    private String columnName;
    // for clients to use in their sorting requests as parameter value and
    // query strings as parameter name
    private String queryParameterName;

    // private
    GuestParameters(String columnName, String sortColumn) {
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
