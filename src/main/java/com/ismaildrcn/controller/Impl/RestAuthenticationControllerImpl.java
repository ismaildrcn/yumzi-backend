package com.ismaildrcn.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.IRestAuthenticationController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.AuthRequest;
import com.ismaildrcn.model.dto.DtoUser;
import com.ismaildrcn.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Override
    @PostMapping("/register")
    public RootEntity<DtoUser> register(@RequestBody AuthRequest authRequest) {
        return ok(authenticationService.register(authRequest));
    }

    @Override
    @PostMapping("/authenticate")
    public RootEntity<?> authenticate(@Valid @RequestBody AuthRequest authRequest) {
        return ok(authenticationService.authenticate(authRequest));
    }

}
