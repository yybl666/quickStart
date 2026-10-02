package com.yblpj.quickstart;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class jwttest {

    @Test
    public void genToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", "1");
        claims.put("username", "张三");

        String token = JWT.create()
                .withClaim("user", claims)                 // 👈 自定义载荷，key 是 user
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000*60*60*3))  // 3小时后过期
                .sign(Algorithm.HMAC256("itheima"));       // 密钥是 itheima

        System.out.println(token);
    }

    @Test
    public void verifyToken() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiMSIsInVzZXJuYW1lIjoi5byg5LiJIn0sImV4cCI6MTc5MDk2NDM1OH0.dwHxIx9Ujj-QXo4KqfqZ6WIUmuHJ4lqBm5WIgcc_BSA";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("itheima")).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        System.out.println(claims.get("user"));
    }

}
