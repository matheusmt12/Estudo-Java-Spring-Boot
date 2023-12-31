package com.matheusmt.pvd.pvd.Security;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private int expiration;
    @Value(("${security.jwt.key}"))
    private String key;

    @Deprecated
    public String generateToken(String username){

        Calendar currentTimeNow = Calendar.getInstance();
        currentTimeNow.add(Calendar.MINUTE,expiration);

        Date dateExpiration = currentTimeNow.getTime();

        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(username)
                .expiration(dateExpiration)
                .signWith(secretKey)
                .compact();

    }
}
