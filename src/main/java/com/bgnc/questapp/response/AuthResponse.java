package com.bgnc.questapp.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component

public class AuthResponse {
    private Long userId;
    private String message;
    private String accessToken;
    private String refreshToken;


}
