package com.ismaildrcn.service;

import com.ismaildrcn.model.dto.DtoUserRequest;
import com.ismaildrcn.model.dto.DtoUserResponse;

public interface IUserService {

    public DtoUserResponse updateUserById(Long id, DtoUserRequest dtoUserRequest);

    public DtoUserResponse getUserById(Long id);

    public void deleteUserById(Long id);

}
