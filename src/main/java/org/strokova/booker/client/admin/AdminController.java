package org.strokova.booker.client.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 17.11.2016.
 */
@RestController
@RequestMapping("/admin/hotels")
public class AdminController {

    @Value("${oauth.resource}")
    private String baseUrl;

    private final OAuth2RestOperations restTemplate;

    @Autowired
    public AdminController(OAuth2RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String hotels() {
        return restTemplate.getForObject(baseUrl + "/hotels", String.class);
    }
}
