package com.moviehub.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;

public class SecurityConfiguration {

    @Configuration
    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private JwtHelper jwtHelper;

        @Bean
        public JwtHelper jwtHelper() {
            return new JwtHelper();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {

            final CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Collections.singletonList("*"));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
            configuration.setAllowCredentials(false);
            configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

            UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
            urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
            return urlBasedCorsConfigurationSource;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            System.out.println(http.toString());
            http
                    .cors()
                    .and()
                    .csrf()
                    .disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                    .and()
                    .addFilterAfter(new JwtFilter(jwtHelper), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/watchlist/api/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/user/api/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/movie/api/**").permitAll()
                    .anyRequest()
                    .authenticated();
        }
    }
}
