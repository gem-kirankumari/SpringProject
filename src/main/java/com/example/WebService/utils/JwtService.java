package com.example.WebService.utils;

import com.example.WebService.model.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtService {
    private String SECRET_KEY = System.getProperty("SECRET");

    public String createToken(UserModel userModel) {
        log.info(System.getProperty("SECRET"));
        return Jwts.builder()
                .setSubject(userModel.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public String getUserFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}
