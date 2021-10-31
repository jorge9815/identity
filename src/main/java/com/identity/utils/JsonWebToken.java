package com.identity.utils;

import com.identity.roles.aplication.RoleDto;
import com.identity.users.aplication.AppUserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class JsonWebToken {
    @SuppressWarnings("deprecation")
    public static String generateJwtToken(PrivateKey privateKey, AppUserDto userDto) {
        userDto.setPassword(null);
        userDto.setSalt(null);
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + 1440 * 60 * 1000))
                .claim("user", userDto)
                .signWith(SignatureAlgorithm.RS256, privateKey).compact();
    }

    public static AppUserDto decodeJwtToken(PrivateKey privateKey, String jwt) {
        var userClaim = (HashMap) Jwts.parser().setSigningKey(privateKey).parseClaimsJws(jwt).getBody().get("user");
        String id = userClaim.get("id").toString();
        String name = userClaim.get("name").toString();
        String user = userClaim.get("user").toString();
        var rolesList = ((List) userClaim.get("rolesList"))
                .stream().map(roleData -> JsonWebToken.getRoleFromHashMap((HashMap)roleData))
                .collect(Collectors.toList());


        System.out.println(rolesList);
        return new AppUserDto(id, name, user, "", (List<RoleDto>) rolesList);
    }

    private static RoleDto getRoleFromHashMap(HashMap roleData) {
        return new RoleDto(
                (roleData).get("id").toString(),
                (roleData).get("name").toString()
        );
    }
}
