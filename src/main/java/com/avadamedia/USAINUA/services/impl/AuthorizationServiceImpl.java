package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.services.AuthService;
import com.avadamedia.USAINUA.auth.JwtRequest;
import com.avadamedia.USAINUA.auth.JwtResponse;
import com.avadamedia.USAINUA.jwt.JwtTokenProvider;
import com.avadamedia.USAINUA.entity.Users;
import com.avadamedia.USAINUA.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UsersService usersService;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        Users user = usersService.getByEmail(loginRequest.getUsername());
        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getEmail());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(user.getId(), user.getEmail(), user.getRoles()));
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getEmail()));
        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}
