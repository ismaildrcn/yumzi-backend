package com.ismaildrcn.service;

import java.util.UUID;

import com.ismaildrcn.model.dto.DtoUserRequest;
import com.ismaildrcn.model.dto.DtoUserResponse;

public interface IUserService {

    public DtoUserResponse updateUserByUniqueId(UUID uniqueId, DtoUserRequest dtoUserRequest);

    public DtoUserResponse getUserByUniqueId(UUID uniqueId);

    public void deleteUserByUniqueId(UUID uniqueId);
}
