package com.ismaildrcn.controller;

import com.ismaildrcn.model.dto.DtoUserRequest;

public interface IRestUserController {

    public RootEntity<?> updateUserById(Long id, DtoUserRequest dtoUserRequest);

    public RootEntity<?> getUserById(Long id);

    public RootEntity<?> deleteUserById(Long id);

}
