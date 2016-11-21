package org.strokova.booker.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.strokova.booker.common.model.GuestReservation;
import org.strokova.booker.common.model.Reservation;
import org.strokova.booker.api.service.ReservationService;

import java.util.Date;

import static org.strokova.booker.common.queryParameters.ReservationQueryParameters.*;

/**
 * 03.11.2016.
 */
@RestController
@RequestMapping("/api/hotels/{hotelId}/rooms/{roomId}/reservations")
public class ReservationController {

    private static final String DEFAULT_PAGE_SIZE = "25";
    private static final String DEFAULT_PAGE_NUMBER = "0";
    private static final String DEFAULT_SORT_ORDER = "ASC";

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Reservation>> readReservations(
            @PathVariable Integer hotelId,
            @PathVariable Long roomId,
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER, required = false) Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer size,
            @RequestParam(value = "order", defaultValue = DEFAULT_SORT_ORDER, required = false) String order,
            @RequestParam(value = "by", defaultValue = RESERVATION_QUERY_PARAM_ROOM, required = false) String by,
            @RequestParam(value = RESERVATION_QUERY_PARAM_DATE_FROM, required = false) Date dateFrom,
            @RequestParam(value = RESERVATION_QUERY_PARAM_DATE_TO, required = false) Date dateTo,
            @RequestParam(value = RESERVATION_QUERY_PARAM_GUEST, required = false) Long guestId) {
        return new ResponseEntity<>(
                reservationService.findReservations(hotelId, roomId, page, size, order, by, dateFrom, dateTo, guestId),
                HttpStatus.OK);
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

    @RequestMapping(value = "/{reservationId}", method = RequestMethod.GET)
    public ResponseEntity<Reservation> readReservation(
            @PathVariable Integer hotelId,
            @PathVariable Long roomId,
            @PathVariable Long reservationId) {
        return new ResponseEntity<>(reservationService.findReservation(hotelId, roomId, reservationId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{reservationId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteReservation(
            @PathVariable Integer hotelId,
            @PathVariable Long roomId,
            @PathVariable Long reservationId) {
        reservationService.deleteReservation(hotelId, roomId, reservationId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{reservationId}", method = RequestMethod.PUT)
    public ResponseEntity<Reservation> updateReservation(
            @RequestBody GuestReservation input,
            @PathVariable Integer hotelId,
            @PathVariable Long roomId,
            @PathVariable Long reservationId) {
        return new ResponseEntity<>(
                reservationService.updateReservation(input, hotelId, roomId, reservationId),
                HttpStatus.OK);
    }
}
