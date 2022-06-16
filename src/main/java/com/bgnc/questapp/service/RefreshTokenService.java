package com.bgnc.questapp.service;

import com.bgnc.questapp.model.RefreshToken;
import com.bgnc.questapp.model.User;
import com.bgnc.questapp.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${refresh.token.expires.in}")
    private Long expireSeconds;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    public boolean isRefreshExpired(RefreshToken token){
        return token.getExpiryDate().before(new Date());

    }

    public String createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
        refreshTokenRepository.save(refreshToken);

        return refreshToken.getToken();
    }
}
