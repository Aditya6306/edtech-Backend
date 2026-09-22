package com.learnify_backend.security;

import org.springframework.stereotype.Component;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;


import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;



@Component
public class JwtTokenProvider {
    @Value("${app.jwt-Secret}")
    private String jwtSecret;
    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    //create generate token method
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationMs);
        return Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(key())
                .compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //get username from token
    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //validate token
    public boolean validateToken(String token){
        Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);

        return true;
    }
}
