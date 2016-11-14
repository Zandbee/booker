package org.strokova.booker.api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * 14.11.2016.
 */
@Configuration
@EnableResourceServer
// @EnableResourceServer annotation enables a Spring Security filter that authenticates requests using an incoming OAuth2 token
// ResourceServerConfigurer provides methods to adjust the access rules and paths that are protected by OAuth2 security
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "booker_api";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .anonymous()
                .disable()
                .requestMatchers()
                .antMatchers("/user/**") // TODO: fix this and below
                .and()
                .authorizeRequests()
                .antMatchers("/user/**")
                .access("hasRole('ADMIN')")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

    // http://websystique.com/spring-security/secure-spring-rest-api-using-oauth2/
}
