package com.identity.utils;
//https://www.sneppets.com/java/util/create-jwt-token-and-sign-with-rsa-private-key/

import com.identity.users.aplication.AppUserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.util.Date;

public class JsonWebToken {
    @SuppressWarnings("deprecation")
    public static String generateJwtToken(PrivateKey privateKey, AppUserDto userDto) {
        return Jwts.builder()
                .setSubject("adam")
                .setExpiration(new Date(2018, 1, 1))
                .setIssuer("info@wstutorial.com")
                .claim("user", userDto)
                .signWith(SignatureAlgorithm.RS256, privateKey).compact();
    }
}
