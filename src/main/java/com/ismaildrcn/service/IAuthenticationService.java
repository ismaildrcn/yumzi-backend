package com.ismaildrcn.service;

import com.ismaildrcn.model.dto.AuthRequest;
import com.ismaildrcn.model.dto.AuthResponse;
import com.ismaildrcn.model.dto.DtoUserResponse;
import com.ismaildrcn.model.dto.RefreshTokenRequest;

public interface IAuthenticationService {

    public DtoUserResponse register(AuthRequest authRequest);

    public AuthResponse authenticate(AuthRequest authRequest);

    public AuthResponse refreshToken(RefreshTokenRequest refreshToken);

}
