package com.example.Elearning.Security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.Elearning.Models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Service 
public class JwtService {

     @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration}")
    private long expiration;


      private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
            secret.getBytes(StandardCharsets.UTF_8)
        );
    }

     public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("email", user.getEmail());
        claims.put("accountId", user.getAccountId());
        claims.put("name", user.getName());
        claims.put("role", user.getRole().getName());

        return Jwts.builder()
                .claims(claims)
                .issuer(issuer)
                .issuedAt(new Date())
                .expiration(
                    new Date(
                        System.currentTimeMillis()
                        + (expiration * 60 * 1000)
                    )
                )
                .signWith(getSigningKey())
                .compact();
    }

      public Claims extractAllClaims(String token) {
        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public <T> T extractSpecificClaim(String token, String key
        , Class<T> type) {

        Claims claims = extractAllClaims(token);
        return claims.get(key, type);
    }

    public <T> T extractClaim(String token, Function<Claims,T> res) {
        return res.apply(extractAllClaims(token));
    }

    public boolean isTokenExpired(String token) {
    return extractExpiration(token)
            .before(new Date());
}

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
}
