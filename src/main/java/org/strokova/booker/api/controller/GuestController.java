package org.strokova.booker.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.strokova.booker.api.model.Guest;
import org.strokova.booker.api.service.GuestService;

import static org.strokova.booker.api.queryParameters.GuestQueryParameters.*;

/**
 * 02.11.2016.
 */
@RestController
@RequestMapping("/guests")
public class GuestController {

    private static final String DEFAULT_PAGE_SIZE = "25";
    private static final String DEFAULT_PAGE_NUMBER = "0";
    private static final String DEFAULT_SORT_ORDER = "ASC";

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Guest>> readGuests(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER, required = false) Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer size,
            @RequestParam(value = "order", defaultValue = DEFAULT_SORT_ORDER, required = false) String order,
            @RequestParam(value = "by", defaultValue = GUEST_QUERY_PARAM_ID, required = false) String by,
            @RequestParam(value = GUEST_QUERY_PARAM_NAME, required = false) String name,
            @RequestParam(value = GUEST_QUERY_PARAM_PHONE, required = false) String phone) {
        return new ResponseEntity<>(guestService.readGuests(page, size, order, by, name, phone), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Guest> add(@RequestBody Guest input) {
        Guest guest = guestService.saveGuest(input);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(guest.getId()).toUri());
        return new ResponseEntity<>(guest, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{guestId}", method = RequestMethod.GET)
    public ResponseEntity<Guest> readGuest(@PathVariable Long guestId) {
        return new ResponseEntity<>(guestService.readGuest(guestId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{guestId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteGuest(@PathVariable Long guestId) {
        guestService.deleteGuest(guestId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{guestId}", method = RequestMethod.PUT)
    public ResponseEntity<Guest> updateGuest(@PathVariable Long guestId, @RequestBody Guest input) {
        return new ResponseEntity<>(guestService.updateGuest(guestId, input), HttpStatus.OK);
    }
}
