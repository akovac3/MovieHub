package com.moviehub.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JwtHelper implements Serializable {
    private static int jwtDuration;
    private static String jwtKey;

    public static int getJwtDuration() {
        return jwtDuration;
    }

    public static String getJwtKey() {
        return jwtKey;
    }

    @Value("${application.jwtDuration}")
    public void setJwtDuration(int duration) {
        JwtHelper.jwtDuration = duration;
    }

    @Value("${application.jwtKey}")
    public void setJwtKey(String jwtSecret) {
        JwtHelper.jwtKey = jwtSecret;
    }
}

