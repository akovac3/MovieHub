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

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

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

        private final String[] adminRoutes = {"/user-service/api/**"};

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

       /* @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.sessionManagement().sessionCreationPolicy(STATELESS);
            http.authorizeRequests().antMatchers("/login", "/api/token/refresh/**").permitAll();
            http.authorizeRequests().antMatchers(GET, "/api/watchlist/**").hasAnyAuthority("ROLE_USER");
            http.authorizeRequests().antMatchers(POST, "/api/user/**").hasAnyAuthority("ROLE_ADMIN");
            //http.authorizeRequests().anyRequest().authenticated();
            http.authorizeRequests().anyRequest().permitAll();
            http.addFilterAfter(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        }*/

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
                    .antMatchers(HttpMethod.POST, "/user/api/auth/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/watchlist/api/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/movie/api/**").permitAll()
                    .antMatchers(adminRoutes).hasRole("ADMIN")
                    .anyRequest()
                    .authenticated();
        }

    }
}
