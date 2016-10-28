package org.strokova.booker.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.strokova.booker.api.domain.Hotel;
import org.strokova.booker.api.service.HotelService;

/**
 * 27.10.2016.
 */

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> showHotels() {
        return new ResponseEntity<>(hotelService.findHotels(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Hotel input) {
        Hotel hotel = hotelService.saveHotel(input);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(hotel.getId()).toUri());
        return new ResponseEntity<>(hotel, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{hotelId}", method = RequestMethod.GET)
    public ResponseEntity<?> showHotel(@PathVariable Integer hotelId) {
        return new ResponseEntity<>(hotelService.findHotel(hotelId), HttpStatus.OK);
    }

}
