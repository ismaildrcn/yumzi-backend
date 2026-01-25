package com.ismaildrcn.controller;

import com.ismaildrcn.model.dto.DtoUserRequest;
import com.ismaildrcn.model.entity.User;

public interface IRestUserController {

    public RootEntity<?> updateUser(User user, DtoUserRequest dtoUserRequest);

    public RootEntity<?> getUser(User user);

    public RootEntity<?> deleteUser(User user);
}
