
package com.learningplanner.config;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Component;
import java.security.Key; import java.util.Date;
@Component
public class JwtUtil {
    @Value("${app.jwt.secret}") private String jwtSecret;
    @Value("${app.jwt.expirationMs}") private long jwtExpirationMs;
    private Key getKey(){ return Keys.hmacShaKeyFor(jwtSecret.getBytes()); }
    public String generateToken(String phone){ Date now=new Date(); Date exp=new Date(now.getTime()+jwtExpirationMs);
        return Jwts.builder().setSubject(phone).setIssuedAt(now).setExpiration(exp).signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }
    public String getPhoneFromToken(String token){ return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject(); }
    public boolean validate(String token){ try{ Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token); return true;}catch(Exception e){return false;} }
}
