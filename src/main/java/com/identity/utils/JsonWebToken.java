package com.identity.utils;
//https://www.sneppets.com/java/util/create-jwt-token-and-sign-with-rsa-private-key/

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.util.Date;

public class JsonWebToken {
    @SuppressWarnings("deprecation")
    public String generateJwtToken(PrivateKey privateKey) {
        return Jwts.builder()
                .setSubject("adam")
                .setExpiration(new Date(2018, 1, 1))
                .setIssuer("info@wstutorial.com")
                .claim("roles", new String[] { "user", "admin" })
                // RS256 with privateKey
                .signWith(SignatureAlgorithm.RS256, privateKey).compact();
    }
}
