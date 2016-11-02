package org.strokova.booker.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.strokova.booker.api.model.Room;
import org.strokova.booker.api.model.RoomType;
import org.strokova.booker.api.queryParameters.RoomQueryParameters;
import org.strokova.booker.api.service.RoomService;

import static org.strokova.booker.api.queryParameters.RoomQueryParameters.*;

/**
 * 02.11.2016.
 */
@RestController
@RequestMapping("/hotels/{hotelId}/rooms")
public class RoomController {

    private static final String DEFAULT_PAGE_SIZE = "25";
    private static final String DEFAULT_PAGE_NUMBER = "0";
    private static final String DEFAULT_SORT_ORDER = "ASC";

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Room>> readRooms(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER, required = false) Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer size,
            @RequestParam(value = "order", defaultValue = DEFAULT_SORT_ORDER, required = false) String order,
            @RequestParam(value = "by", defaultValue = ROOM_QUERY_PARAM_ID, required = false) String by,
            @RequestParam(value = ROOM_QUERY_PARAM_TYPE, required = false) RoomType type,
            @RequestParam(value = ROOM_QUERY_PARAM_HAS_TV, required = false) Boolean hasTv,
            @RequestParam(value = ROOM_QUERY_PARAM_HAS_BALCONY, required = false) Boolean hasBalcony,
            @RequestParam(value = ROOM_QUERY_PARAM_HAS_AIR_CONDITIONER, required = false) Boolean hasAirConditioner,
            @RequestParam(value = ROOM_QUERY_PARAM_HAS_RUBBISH_VIEW, required = false) Boolean hasRubbishView,
            @RequestParam(value = ROOM_QUERY_PARAM_HAS_POOL_VIEW, required = false) Boolean hasPoolView,
            @RequestParam(value = ROOM_QUERY_PARAM_HAS_SEA_VIEW, required = false) Boolean hasSeaView,
            @RequestParam(value = ROOM_QUERY_PARAM_HAS_FIXED_DATE_RESERVATION, required = false) Boolean hasFixedDateReservation) {
        return new ResponseEntity<>(
                roomService.findRooms(page, size, order, by,
                        type, hasTv, hasBalcony, hasAirConditioner,
                        hasRubbishView, hasPoolView, hasSeaView, hasFixedDateReservation),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Room> add(
            @RequestBody Room input,
            @PathVariable Integer hotelId) {
        Room room = roomService.saveRoom(input, hotelId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(room.getId()).toUri());
        return new ResponseEntity<>(room, httpHeaders, HttpStatus.CREATED);
    }
}
