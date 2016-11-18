package org.strokova.booker.client.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import org.strokova.booker.model.GuestReservation;
import org.strokova.booker.model.Reservation;

/**
 * 17.11.2016.
 */
@Controller
@RequestMapping("/admin/reservations")
public class ReservationAdminController {

    @Value("${oauth.resource}")
    private String baseUrl; // TODO: add "/api" to base

    private final OAuth2RestOperations restTemplate;

    @Autowired
    public ReservationAdminController(OAuth2RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showPage() {
        return "fixedDatesReservation";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Reservation> add(
            @RequestBody GuestReservation input,
            @RequestParam("hotelId") Integer hotelId,
            @RequestParam("roomId") Long roomId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl(baseUrl + "/admin")
                        .pathSegment("hotels", hotelId.toString(), "rooms", roomId.toString(), "reservations");
        ResponseEntity<Reservation> response = restTemplate.postForEntity(
                uriBuilder.build().encode().toUri(),
                input,
                Reservation.class);
        return response;
    }

    // TODO: add PUT here to edit reservations?
}
