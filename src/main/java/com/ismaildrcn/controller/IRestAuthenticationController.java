package com.ismaildrcn.controller;

import com.ismaildrcn.model.dto.AuthRequest;
import com.ismaildrcn.model.dto.DtoUserResponse;
import com.ismaildrcn.model.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

    public RootEntity<DtoUserResponse> register(AuthRequest authRequest);

    public RootEntity<?> authenticate(AuthRequest authRequest);

    public RootEntity<?> refreshToken(RefreshTokenRequest refreshTokenRequest);

}
