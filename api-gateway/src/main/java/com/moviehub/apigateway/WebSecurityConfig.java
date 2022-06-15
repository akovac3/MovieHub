package com.moviehub.apigateway;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;

@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    private AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
                ).accessDeniedHandler((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
                ).and()
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(HttpMethod.POST, "/user/login").permitAll()
                .pathMatchers(HttpMethod.POST,"/user/signup").permitAll()
                .pathMatchers(HttpMethod.POST, "/user/signup/admin").hasAuthority("ROLE_ADMIN")
                .pathMatchers(HttpMethod.GET, "/movie/api/movie/").permitAll()
                .pathMatchers(HttpMethod.GET, "/movie/api/actor/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/movie/api/genre/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/movie/api/movie/**").permitAll()
                .pathMatchers(HttpMethod.POST, "/movie/api/movie/").permitAll()
                .pathMatchers("/user/api/user", "/user/api/service", "/user/api/role").permitAll()
                .pathMatchers("/main/api/watchlist").hasAuthority("ROLE_USER")
                .pathMatchers(HttpMethod.DELETE, "main/api/watchlist/{id}").hasAuthority("ROLE_ADMIN")
                .anyExchange().authenticated()
                .and().build();
    }

}