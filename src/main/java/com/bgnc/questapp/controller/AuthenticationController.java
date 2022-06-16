package com.bgnc.questapp.controller;

import com.bgnc.questapp.model.RefreshToken;
import com.bgnc.questapp.model.User;
import com.bgnc.questapp.request.UserRequest;
import com.bgnc.questapp.response.AuthResponse;
import com.bgnc.questapp.security.JwtTokenProvider;
import com.bgnc.questapp.service.RefreshTokenService;
import com.bgnc.questapp.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@NoArgsConstructor
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private AuthResponse authResponse;

    private RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserRequest loginRequest){

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtTokenProvider.generateJwtToken(authenticationToken);
        User user = userService.getUserByUsername(loginRequest.getUsername());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setMessage("Bearer "+jwtToken);
        authResponse.setUserId(user.getId());

        return authResponse;

    }



    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest registerRequest){

        if(userService.getUserByUsername(registerRequest.getUsername())!=null){
            authResponse.setMessage("Username already in use");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);

        }
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userService.addUser(user);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(registerRequest.getUsername(),registerRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);

        authResponse.setMessage("User successfully is registered in system");
        authResponse.setAccessToken("Bearer "+jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setUserId(user.getId());

        return new ResponseEntity<>(authResponse,HttpStatus.CREATED);


    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody User user){

        return null;
    }



}
