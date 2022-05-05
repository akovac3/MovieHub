package com.moviehub.apigateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import java.util.stream.Collectors;

public class JwtFilter extends OncePerRequestFilter {
    private final JwtHelper helper;
    public JwtFilter(JwtHelper helper) {
        this.helper = helper;
    }

    private List<GrantedAuthority> fetchRoles(String x) {
        List<String> userRoles = new ArrayList<>();
        userRoles.add("ROLE_USER");
        if (x.toLowerCase().contains("admin"))  {
            userRoles.add("ROLE_ADMIN");
        }
        return userRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");

        if(requestHeader == null || !requestHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        String token = requestHeader.replace("Bearer ", "");

        try {
            Claims claims = Jwts.parser().setSigningKey(helper.getJwtKey()).parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            if(username != null) {

                List<String> authorities = (List<String>) claims.get("authorities");
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, fetchRoles(claims.get("authorities").toString()));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(request, response);
    }
}
