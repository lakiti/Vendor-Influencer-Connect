package com.vendorinfluencer.service;


import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Date;
import java.util.function.Function;

public interface JwtService {
    String generateToken(String username);

    String extractUsername(String username);

    Date extractExpiration(String token);

     <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    Claims extractAllClaims(String token);

     Boolean isTokenExpired(String token);

     Boolean validateToken(String token, UserDetails userDetails);
}
