package org.strokova.booker.common.queryParameters;

/**
 * 02.11.2016.
 */
// TODO: try removing "has". if works, remove in other similar classes
public interface RoomQueryParameters {
    String ROOM_QUERY_PARAM_ID = "id";
    String ROOM_QUERY_PARAM_TYPE = "type";
    String ROOM_QUERY_PARAM_HAS_TV = "hasTv";
    String ROOM_QUERY_PARAM_HAS_BALCONY = "hasBalcony";
    String ROOM_QUERY_PARAM_HAS_AIR_CONDITIONER = "hasAirConditioner";
    String ROOM_QUERY_PARAM_HAS_RUBBISH_VIEW = "hasRubbishView";
    String ROOM_QUERY_PARAM_HAS_POOL_VIEW = "hasPoolView";
    String ROOM_QUERY_PARAM_HAS_SEA_VIEW = "hasSeaView";
    String ROOM_QUERY_PARAM_HAS_FIXED_DATE_RESERVATION = "hasFixedDateReservation";
}
