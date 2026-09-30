package com.elearning.graduation.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.elearning.graduation.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration}")
    private long expiration;


    //Generate the Secret Key
    SecretKey getSignKey()
    {

        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
    //Generate token for the user with the generated Secret key: with id, email and role.
    public String GeneratedToken(User user)
    {
        Map< String, Object> claims = new HashMap<>();
        claims.put("Id", user.getAccountId());
        claims.put("Name", user.getName());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole().getRoleName().name()); //adding only the name not the entire role entity

        return Jwts.builder()
                .claims(claims)
                .issuer(issuer)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (expiration * 60 * 1000)))
                .signWith(getSignKey())
                .compact();
    }


    //extract  claims
    public Claims extractAllClaims(String token)
    {
        //key : value
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    //Extracting Specific Claim

    public <T> T  extractSpecificClaim(String token, String key, Class<T> type)
    {
        Claims claims = extractAllClaims(token); //payload: map with lots of claims
        return claims.get(key, type);
    }

    //take claims and return the value whatever its type is: res: lambda
    public <T> T extractClaim(String token, Function<Claims,T> res)
    {
        return res.apply(extractAllClaims(token));
    }

    public Date extractExpiration(String token) {
        //claims -> claims.getExpiration() : when we receive:   Claims object, call its getExpiration() method:
        return extractClaim(token, Claims::getExpiration);
    }
//    expired token
//    → isTokenExpired() = true
//            → !true = false
//            → rejected
//
//    valid token
//    → isTokenExpired() = false
//            → !false = true
//            → accepted

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}