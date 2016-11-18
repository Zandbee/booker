package org.strokova.booker.client.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

import static org.strokova.booker.api.security.OAuthScopes.*;

/**
 * 17.11.2016.
 */
@Configuration
@EnableOAuth2Client
public class UserOAuth2Client {

    private static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";

    @Value("${oauth.token}")
    private String oAuthTokenUrl;

    @Bean
    public OAuth2RestOperations restTemplate() {
        return new OAuth2RestTemplate(resourceDetails(), new DefaultOAuth2ClientContext());
    }

    private OAuth2ProtectedResourceDetails resourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();

        details.setClientId("user_client");
        details.setClientSecret("user_secret");
        details.setAccessTokenUri(oAuthTokenUrl);
        details.setGrantType(GRANT_TYPE_CLIENT_CREDENTIALS);
        details.setScope(Arrays.asList(READ.getName(), WRITE.getName()));

        return details;
    }
}
