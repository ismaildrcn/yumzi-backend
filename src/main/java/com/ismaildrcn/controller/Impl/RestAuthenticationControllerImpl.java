package com.ismaildrcn.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.IRestAuthenticationController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.AuthRequest;
import com.ismaildrcn.model.dto.DtoUserResponse;
import com.ismaildrcn.model.dto.RefreshTokenRequest;
import com.ismaildrcn.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Override
    @PostMapping("/register")
    public RootEntity<DtoUserResponse> register(@RequestBody AuthRequest authRequest) {
        return ok(authenticationService.register(authRequest));
    }

    @Override
    @PostMapping("/authenticate")
    public RootEntity<?> authenticate(@Valid @RequestBody AuthRequest authRequest) {
        return ok(authenticationService.authenticate(authRequest));
    }

    @Override
    @PostMapping("/refresh-token")
    public RootEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}
