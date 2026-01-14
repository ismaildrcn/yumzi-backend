package com.ismaildrcn.controller;

import java.util.UUID;

import com.ismaildrcn.model.dto.DtoUserRequest;

public interface IRestUserController {

    public RootEntity<?> updateUserByUniqueId(UUID uniqueId, DtoUserRequest dtoUserRequest);

    public RootEntity<?> getUserByUniqueId(UUID uniqueId);

    public RootEntity<?> deleteUserByUniqueId(UUID uniqueId);
}
