package com.ismaildrcn.controller;

import com.ismaildrcn.model.dto.AuthRequest;
import com.ismaildrcn.model.dto.DtoUser;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest authRequest);

    public RootEntity<?> authenticate(AuthRequest authRequest);

}
