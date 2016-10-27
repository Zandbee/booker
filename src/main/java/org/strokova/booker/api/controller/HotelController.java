package org.strokova.booker.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 27.10.2016.
 */

@RestController
public class HotelController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String showHotels() {
        return "HOTELS";
    }
}
