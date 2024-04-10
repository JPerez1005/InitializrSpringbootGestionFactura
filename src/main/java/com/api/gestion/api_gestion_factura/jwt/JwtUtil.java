package com.api.gestion.api_gestion_factura.jwt;

import java.util.function.Function;
import org.springframework.stereotype.Service;

/**
 * @author perez
 */

@Service
public class JwtUtil {
    
    private String secret="springboot";
    
    public String extractUsername(String token){
        return null;
    }
    
//    public <T> T extractClaims(String token,Function<Claims, T> claimsResolver){
//        final Claims claims=extractClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    
//    public Claims extractAllClaims(String token){
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//    }
    
}
