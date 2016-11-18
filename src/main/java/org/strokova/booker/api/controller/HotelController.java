package org.strokova.booker.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.strokova.booker.model.Hotel;
import org.strokova.booker.api.service.HotelService;

import static org.strokova.booker.api.queryParameters.HotelQueryParameters.*;

/**
 * 27.10.2016.
 */

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private static final String DEFAULT_PAGE_SIZE = "25";
    private static final String DEFAULT_PAGE_NUMBER = "0";
    private static final String DEFAULT_SORT_ORDER = "ASC";

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Hotel>> readHotels(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER, required = false) Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer size,
            @RequestParam(value = "order", defaultValue = DEFAULT_SORT_ORDER, required = false) String order,
            @RequestParam(value = "by", defaultValue = HOTEL_QUERY_PARAM_ID, required = false) String by,
            @RequestParam(value = HOTEL_QUERY_PARAM_NAME, required = false) String name,
            @RequestParam(value = HOTEL_QUERY_PARAM_HAS_POOL, required = false) Boolean hasPool,
            @RequestParam(value = HOTEL_QUERY_PARAM_HAS_WATERPARK, required = false) Boolean hasWaterpark,
            @RequestParam(value = HOTEL_QUERY_PARAM_HAS_TENNIS_COURT, required = false) Boolean hasTennisCourt
    ) {
        return new ResponseEntity<>(
                hotelService.findHotels(page, size, order, by, name, hasPool, hasWaterpark, hasTennisCourt),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Hotel> add(@RequestBody Hotel input) {
        Hotel hotel = hotelService.saveHotel(input);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(hotel.getId()).toUri());
        return new ResponseEntity<>(hotel, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{hotelId}", method = RequestMethod.GET)
    public ResponseEntity<Hotel> readHotel(@PathVariable Integer hotelId) {
        // TODO: NPE when hotel with hotelId not found
        return new ResponseEntity<>(hotelService.findHotel(hotelId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{hotelId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteHotel(@PathVariable Integer hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{hotelId}", method = RequestMethod.PUT)
    public ResponseEntity<Hotel> updateHotel(
            @PathVariable Integer hotelId,
            @RequestBody Hotel hotel) {
        return new ResponseEntity<>(hotelService.updateHotel(hotelId, hotel), HttpStatus.OK);
    }
}
