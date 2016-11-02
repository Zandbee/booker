package org.strokova.booker.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.strokova.booker.api.model.Room;
import org.strokova.booker.api.service.RoomService;

/**
 * 02.11.2016.
 */
@RestController
@RequestMapping("/hotels/{hotelId}/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
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
