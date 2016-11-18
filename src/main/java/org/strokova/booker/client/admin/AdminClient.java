package org.strokova.booker.client.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static org.strokova.booker.api.security.OAuthScopes.*;

/**
 * 17.11.2016.
 */
@Configuration
@EnableOAuth2Client
public class AdminClient {

    private static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";

    @Value("${oauth.authorize}")
    private String oAuthAuthorizeUrl;

    @Value("${oauth.token}")
    private String oAuthTokenUrl;

    @Bean
    public OAuth2RestOperations restTemplate() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        //details.setUserAuthorizationUri(oAuthAuthorizeUrl);
        details.setClientId("admin_client");
        details.setClientSecret("admin_secret");
        details.setAccessTokenUri(oAuthTokenUrl);
        details.setGrantType(GRANT_TYPE_CLIENT_CREDENTIALS);
        details.setScope(Arrays.asList(TRUST.getName()));

        final OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(details, new DefaultOAuth2ClientContext());

        return restTemplate;
    }
}
