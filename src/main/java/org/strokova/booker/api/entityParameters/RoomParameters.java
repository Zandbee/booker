package org.strokova.booker.api.entityParameters;

import static org.strokova.booker.common.queryParameters.RoomQueryParameters.*;

/**
 * 02.11.2016.
 */
public enum RoomParameters {
    ID("id", ROOM_QUERY_PARAM_ID),
    TYPE("type", ROOM_QUERY_PARAM_TYPE),
    HAS_TV("hasTv", ROOM_QUERY_PARAM_HAS_TV),
    HAS_BALCONY("hasBalcony", ROOM_QUERY_PARAM_HAS_BALCONY),
    HAS_AIR_CONDITIONER("hasAirConditioner", ROOM_QUERY_PARAM_HAS_AIR_CONDITIONER),
    HAS_RUBBISH_VIEW("hasRubbishView", ROOM_QUERY_PARAM_HAS_RUBBISH_VIEW),
    HAS_POOL_VIEW("hasPoolView", ROOM_QUERY_PARAM_HAS_POOL_VIEW),
    HAS_SEA_VIEW("hasSeaView" , ROOM_QUERY_PARAM_HAS_SEA_VIEW),
    HAS_FIXED_DATE_RESERVATION("hasFixedDateReservation", ROOM_QUERY_PARAM_HAS_FIXED_DATE_RESERVATION);

    // exact field name in class, corresponding to column in db
    private String columnName;
    // for clients to use in their sorting requests as parameter value and
    // query strings as parameter name
    private String queryParameterName;

    // private
    RoomParameters(String columnName, String sortColumn) {
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
