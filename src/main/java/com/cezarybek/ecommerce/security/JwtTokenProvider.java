package com.cezarybek.ecommerce.security;


import com.cezarybek.ecommerce.exception.AuthenticationException;
import com.cezarybek.ecommerce.model.User;
import com.cezarybek.ecommerce.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    @Autowired
    private UserRepository userRepository;

    public String generateToken(Authentication authentication) {
        log.info("Generating JWT token...");
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).get();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);


        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("id", user.getId().toString())
                .claim("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        log.info("Getting payload from JWT token...");
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public void validateToken(String token) {
        try {
            log.info("Validating JWT token...");
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new AuthenticationException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new AuthenticationException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new AuthenticationException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new AuthenticationException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new AuthenticationException("JWT claims string is empty.");
        }

    }
}
