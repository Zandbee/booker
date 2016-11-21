package org.strokova.booker.client.userClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import org.strokova.booker.client.jacksonUtils.JacksonPageImpl;
import org.strokova.booker.common.model.Hotel;

import java.util.Iterator;
import java.util.List;

import static org.strokova.booker.common.queryParameters.HotelQueryParameters.*;
import static org.strokova.booker.common.queryParameters.HotelQueryParameters.HOTEL_QUERY_PARAM_HAS_TENNIS_COURT;

/**
 * 18.11.2016.
 */
@Controller
@RequestMapping("/client/hotels")
@EnableOAuth2Client
public class HotelUserController {

    private static final String DEFAULT_PAGE_SIZE = "25";
    private static final String DEFAULT_PAGE_NUMBER = "0";
    private static final String DEFAULT_SORT_ORDER = "ASC";

    @Value("${oauth.resource}")
    private String baseUrl; // TODO: extract "/api/hotels" into var

    private final OAuth2RestOperations restTemplate;

    @Autowired
    public HotelUserController(OAuth2RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHotels(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER, required = false) Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer size,
            @RequestParam(value = "order", defaultValue = DEFAULT_SORT_ORDER, required = false) String order,
            @RequestParam(value = "by", defaultValue = HOTEL_QUERY_PARAM_ID, required = false) String by,
            @RequestParam(value = HOTEL_QUERY_PARAM_NAME, required = false) String name,
            @RequestParam(value = HOTEL_QUERY_PARAM_HAS_POOL, required = false) Boolean hasPool,
            @RequestParam(value = HOTEL_QUERY_PARAM_HAS_WATERPARK, required = false) Boolean hasWaterpark,
            @RequestParam(value = HOTEL_QUERY_PARAM_HAS_TENNIS_COURT, required = false) Boolean hasTennisCourt
    ) {
        System.out.println(restTemplate.getAccessToken()); // TODO: remove - never print sensitive data :)

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/api/hotels")
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("order", order)
                .queryParam("by", by)
                .queryParam(HOTEL_QUERY_PARAM_NAME, name)
                .queryParam(HOTEL_QUERY_PARAM_HAS_POOL, hasPool)
                .queryParam(HOTEL_QUERY_PARAM_HAS_WATERPARK, hasWaterpark)
                .queryParam(HOTEL_QUERY_PARAM_HAS_TENNIS_COURT, hasTennisCourt);


        ResponseEntity<JacksonPageImpl<Hotel>> responseEntity = restTemplate.exchange(
                uriBuilder.build().encode().toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<JacksonPageImpl<Hotel>>() {});

        JacksonPageImpl<Hotel> responseBody = responseEntity.getBody();
        List<Hotel> hotels = responseBody.getContent();
        Iterator<Sort.Order> sortIterator = responseBody.getSort().iterator();

        //throw new NotImplementedException();
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
