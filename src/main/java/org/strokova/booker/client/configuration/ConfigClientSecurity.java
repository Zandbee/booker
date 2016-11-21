package org.strokova.booker.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * 14.11.2016.
 */
@Configuration
@EnableWebSecurity
public class ConfigClientSecurity extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("adminClient").password("adminClient").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.requiresChannel().and() // TODO: test if this works fine
                .antMatcher("/client").authorizeRequests().anyRequest().permitAll()
                .and()
                .antMatcher("/adminClient/**").authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint()); // TODO: does not work :(
    }

    @Bean
    public BasicAuthenticationEntryPoint entryPoint() {
        BasicAuthenticationEntryPoint basicAuthEntryPoint = new BasicAuthenticationEntryPoint();
        basicAuthEntryPoint.setRealmName("Booker_Admin"); // TODO: need this?
        return basicAuthEntryPoint;
    }
}
