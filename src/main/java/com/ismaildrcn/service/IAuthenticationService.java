package com.ismaildrcn.service;

import com.ismaildrcn.model.dto.AuthRequest;
import com.ismaildrcn.model.dto.DtoUser;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest authRequest);

}
