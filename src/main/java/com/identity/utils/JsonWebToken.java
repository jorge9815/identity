package com.identity.utils;

import com.identity.users.aplication.AppUserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.util.Date;

public class JsonWebToken {
    @SuppressWarnings("deprecation")
    public static String generateJwtToken(PrivateKey privateKey, AppUserDto userDto) {
        userDto.setPassword(null);
        userDto.setSalt(null);
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + 1440 * 60 *1000))
                .claim("user", userDto)
                .signWith(SignatureAlgorithm.RS256, privateKey).compact();
    }
}
