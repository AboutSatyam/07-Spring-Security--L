package com.satyam._4securityjwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenService {

    private String secretkey = "mysecretkey123456789012345678901234";
    private String getTheSecretKey() {
        try {
            KeyGenerator key = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secret = key.generateKey();

            return Base64.getEncoder().encodeToString(secret.getEncoded());

        } catch (Exception e) {
            throw new RuntimeException("Error is generating key");
        }
    }

    public String generateToken(String name) {

        // Claims == PayLoad
        // IMPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(name).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1800000)).signWith(getkey(), SignatureAlgorithm.HS256).compact();
    }
//
//    private Key getkey() {
//        byte[] b = Decoders.BASE64.decode(secretkey);
//        return Keys.hmacShaKeyFor(b);
//    }

    private SecretKey getkey() {
        return Keys.hmacShaKeyFor(secretkey.getBytes());
    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//
//    }
//
//    public String extractUserName(String token) {
//    }
//}
//


    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        System.out.println("Token in extract claim " + token);
        final Claims claims = extractAllClaims(token);
        System.out.println("Claims " + claims);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        System.out.println("Token is exactr all claims " + token);
        return Jwts.parser().setSigningKey(getkey()).build().parseClaimsJws(token).getBody();
    }

    //
//    private Claims extractAllClaims(String token) {
//
//        return Jwts.parser()
//                .verifyWith(getkey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
    public boolean validateToken(String token, UserDetails userDetails) {
        //System.out.println("token in validate token " + token);
        final String userName = extractUserName(token);
        // System.out.println("validate Token " + token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        System.out.println("Token is ex");
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
//package com.satyam._4securityjwt.service;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class JwtTokenService {
//
//    private String secretkey;
//
//    public JwtTokenService() {
//        this.secretkey = getTheSecretKey();
//    }
//
//    // Generate Secret Key
//    private String getTheSecretKey() {
//        try {
//            KeyGenerator key = KeyGenerator.getInstance("HmacSHA256");
//            SecretKey secret = key.generateKey();
//
//            return Base64.getEncoder().encodeToString(secret.getEncoded());
//
//        } catch (Exception e) {
//            throw new RuntimeException("Error generating key", e);
//        }
//    }
//
//    // Generate JWT Token
//    public String generateToken(String name) {
//
//        // Claims == Payload
//        Map<String, Object> claims = new HashMap<>();
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(name)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1800000))
//                .signWith(getKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    // Convert Base64 Secret Key into Signing Key
//    private SecretKey getKey() {
//        byte[] b = Decoders.BASE64.decode(secretkey);
//        return Keys.hmacShaKeyFor(b);
//    }
//
//    // Validate JWT Token
//    public Boolean validateToken(String token, UserDetails userDetails) {
//
//        final String username = extractUserName(token);
//
//        return (username.equals(userDetails.getUsername())
//                && !isTokenExpired(token));
//    }
//
//    // Extract Username from JWT Token
//    public String extractUserName(String token) {
//
//        return Jwts.parser()
//                .verifyWith(getKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload()
//                .getSubject();
//    }
//
//    // Check Token Expiration
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    // Extract Expiration Date
//    private Date extractExpiration(String token) {
//
//        return Jwts.parser()
//                .verifyWith(getKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload()
//                .getExpiration();
//    }
//}
