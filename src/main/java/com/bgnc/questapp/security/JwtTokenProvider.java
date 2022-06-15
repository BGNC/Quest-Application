package com.bgnc.questapp.security;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.Date;

@Component
public class JwtTokenProvider {

    /**
     * Token secret
     * time
     */
    @Value("${questapp.app.secret}")
    private String appSecret;
    @Value("${questapp.app.expires.in}")
    private Long expiresIn;

    public String generateJwtToken(Authentication auth){
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
        Date expiredDate = new Date(new Date().getTime()+expiresIn);

        return Jwts.builder().
                setSubject(Long.toString(userDetails.getId())).
                setIssuedAt(new Date()).setExpiration(expiredDate).
                signWith(SignatureAlgorithm.HS512,appSecret).
                compact();

    }

    public Long getUserIdFromJwt(String token){
        Claims claims = Jwts.parser().
                setSigningKey(appSecret).
                parseClaimsJws(token).
                getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(appSecret).parseClaimsJws(token);
            return !isTokenExpired(token);
        }
        catch (SignatureException e)
        {
            return false;
        }
        catch (MalformedJwtException e)
        {
            return false;
        } catch(ExpiredJwtException e)
        {
            return false;
        }
        catch(UnsupportedJwtException e){
            return false;

        }
        catch(IllegalArgumentException e)
        {
            return false;
        }


    }

    private boolean isTokenExpired(String token) {

        Date expiration = Jwts.parser().setSigningKey(appSecret).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

}
