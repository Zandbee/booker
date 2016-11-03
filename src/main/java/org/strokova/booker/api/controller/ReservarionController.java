package org.strokova.booker.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.strokova.booker.api.model.GuestReservation;
import org.strokova.booker.api.model.Reservation;
import org.strokova.booker.api.service.ReservationService;

/**
 * 03.11.2016.
 */
@RestController
@RequestMapping("/hotels/{hotelId}/rooms/{roomId}/reservations")
public class ReservarionController {

    private final ReservationService reservationService;

    @Autowired
    public ReservarionController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Reservation> add(
            @RequestBody GuestReservation input,
            @PathVariable Integer hotelId,
            @PathVariable Long roomId) {
        Reservation reservation = reservationService.save(input, hotelId, roomId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(reservation.getId()).toUri());
        return new ResponseEntity<>(reservation, httpHeaders, HttpStatus.CREATED);
    }
}
