package com.alternova.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String getToken(UserDetails user);

    boolean isTokenValid(String token, UserDetails userDetails);

    String getEmailFromToken(String token);
}
