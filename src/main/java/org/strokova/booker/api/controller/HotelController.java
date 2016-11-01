package org.strokova.booker.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.strokova.booker.api.model.Hotel;
import org.strokova.booker.api.service.HotelService;

import java.util.Collection;
import java.util.Map;

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
    public ResponseEntity<Collection<Hotel>> readHotels() {
        return new ResponseEntity<>(hotelService.findHotels(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Hotel>> readHotels(@RequestParam Map<String, String> pathVariables) {
        return new ResponseEntity<>(hotelService.findHotels(pathVariables), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Hotel>> readHotels(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return new ResponseEntity<>(hotelService.findHotels(page, size), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Hotel>> readHotels(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("order") String order) {
        return new ResponseEntity<>(hotelService.findHotels(page, size, order), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Hotel>> readHotels(
            @RequestParam("by") String by,
            @RequestParam("order") String order) {
        return new ResponseEntity<>(hotelService.findHotels(by, order), HttpStatus.OK);
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
        return new ResponseEntity<>(hotelService.findHotel(hotelId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{hotelName}", method = RequestMethod.GET)
    public ResponseEntity<Hotel> readHotel(@PathVariable String hotelName) {
        return new ResponseEntity<>(hotelService.findHotel(hotelName), HttpStatus.OK);
    }
    // TODO: multiple names in hotelName var?

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
