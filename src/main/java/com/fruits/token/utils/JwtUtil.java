package com.fruits.token.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fruits.token.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil {

    private static String secret;

    public static String createToken(User user) {
        LocalDateTime afterOneMinute = LocalDateTime.now().plusMinutes(1);

        String token = JWT.create().withSubject(user.getUserId().toString())
                .withClaim("un", user.getUsername())
                .withExpiresAt(Date.from(afterOneMinute.atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC256(secret));

        return token;
    }


    public static boolean verifyToken(String token) {
        boolean result = false;

        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
            Date expiresAt = jwt.getExpiresAt();
            if (System.currentTimeMillis() <= expiresAt.getTime()) {
                result = true;
            }
        } catch (Exception e) {
            return result;
        }

        return result;
    }


    @Value("${fruits.jwt.secret}")
    public void setSecret(String secret) {
        JwtUtil.secret = secret;
    }
}
