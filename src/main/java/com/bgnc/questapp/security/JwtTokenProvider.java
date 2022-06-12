package com.bgnc.questapp.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

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

    public String generateJwToken(Authentication auth){
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
        Date expiredDate = new Date(new Date().getTime()+expiresIn);

        return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date()).
                setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512,appSecret)
                .compact();

    }

}
