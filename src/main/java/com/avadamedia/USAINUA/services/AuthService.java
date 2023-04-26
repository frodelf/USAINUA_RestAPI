package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.auth.JwtRequest;
import com.avadamedia.USAINUA.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);

}