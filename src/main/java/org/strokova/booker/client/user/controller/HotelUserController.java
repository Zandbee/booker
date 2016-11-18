package org.strokova.booker.client.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.strokova.booker.model.Hotel;

/**
 * 18.11.2016.
 */
@Controller
@RequestMapping("/client/hotels")
@EnableOAuth2Client
public class HotelUserController {

    @Value("${oauth.resource}")
    private String baseUrl; // TODO: add var for "/api/hotels" to base

    private final OAuth2RestOperations restTemplate;

    @Autowired
    public HotelUserController(OAuth2RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHotels() {
        System.out.println(restTemplate.getAccessToken());
        ResponseEntity response = restTemplate.getForEntity(baseUrl + "/api/hotels", Hotel.class); // TODO: setup UriBuilder
        return "hotels";
    }

    @RequestMapping(value = "/{hotelId}", method = RequestMethod.GET)
    public ResponseEntity<Hotel> getHotel(@PathVariable Integer hotelId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl(baseUrl + "/api/hotels")
                        .pathSegment(hotelId.toString());
        ResponseEntity<Hotel> response = restTemplate.getForEntity(uriBuilder.build().encode().toUri(), Hotel.class);
        return response;
    }
}
