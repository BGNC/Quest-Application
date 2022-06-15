package com.bgnc.questapp.service;

import com.bgnc.questapp.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


}
