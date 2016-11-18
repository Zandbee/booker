package org.strokova.booker.client.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.strokova.booker.model.Hotel;

/**
 * 17.11.2016.
 */
@RestController
@RequestMapping("/admin/hotels")
@EnableOAuth2Client
public class AdminController {

    @Value("${oauth.resource}")
    private String baseUrl; // TODO: add "/api" to base

    private final OAuth2RestOperations restTemplate;

    @Autowired
    public AdminController(OAuth2RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Hotel hotels() {
        System.out.println(restTemplate.getAccessToken());
        return restTemplate.getForObject(baseUrl + "/api/hotels/3", Hotel.class); // TODO: setup UriBuilder
    }
}
