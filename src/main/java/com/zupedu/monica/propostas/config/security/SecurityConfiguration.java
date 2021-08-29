package com.zupedu.monica.propostas.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers(HttpMethod.GET, "/proposta/**").hasAuthority("SCOPE_scope-write")
                        .antMatchers(HttpMethod.POST, "/proposta").hasAuthority("SCOPE_scope-write")
                        .antMatchers(HttpMethod.POST, "/cartao/**").hasAuthority("SCOPE_scope-write")
                        .antMatchers(HttpMethod.PUT, "/cartao/**").hasAuthority("SCOPE_scope-write")
                        .antMatchers(HttpMethod.POST, "/biometria").hasAuthority("SCOPE_scope-write")
                        .antMatchers(HttpMethod.POST, "/actuator/**").hasAuthority("SCOPE_scope-write")
        ).cors()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}

