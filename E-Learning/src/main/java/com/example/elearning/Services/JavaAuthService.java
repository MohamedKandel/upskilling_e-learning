package com.example.elearning.Services;


import com.example.elearning.Models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;


//Work on generating Secret key, generating jwt/token using the secret key
@Service
public class JavaAuthService {

    @Value("${jwt.secret}")
    String secretKey;
    @Value("${jwt.issuer}")
    String issuer;
    @Value("${jwt.expiration}")
    int expiration;

    //Generate the Secret Key
    SecretKey getSignKey()
    {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
    //Generate token for the user with the generated Secret key: with id, email and role.
    public String GeneratedToken(User user)
    {
        Map< String, Object> claims = new HashMap<>();
        claims.put("Id", user.getUserId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());

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


    public boolean isTokenExpired(String token) {
        if(extractExpiration(token).before(new Date(System.currentTimeMillis()))) {
            return false;
        } else {
            return true;
        }
    }



}
