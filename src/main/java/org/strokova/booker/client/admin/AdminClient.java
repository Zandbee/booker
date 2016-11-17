package org.strokova.booker.client.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
@RestController
public class AdminClient {

    private static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";

    @Value("${oauth.authorize}")
    private String oAuthAuthorizeUrl;

    @Value("${oauth.token}")
    private String oAuthTokenUrl;

    @Bean
    public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
        return new OAuth2RestTemplate(resource(), oauth2ClientContext);
    }

    private OAuth2ProtectedResourceDetails resource() {
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri(oAuthTokenUrl);
        //resource.setUserAuthorizationUri(oAuthAuthorizeUrl);
        resource.setClientId("admin_client");
        resource.setClientSecret("admin_secret");
        resource.setGrantType(GRANT_TYPE_CLIENT_CREDENTIALS);
        resource.setScope(Arrays.asList(TRUST.getName()));

        return resource ;
    }
}
