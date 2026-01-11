package com.ismaildrcn.controller;

import com.ismaildrcn.model.dto.DtoUserIU;

public interface IRestUserController {

    public RootEntity<?> updateUserById(Long id, DtoUserIU dtoUserIU);

    public RootEntity<?> getUserById(Long id);

    public RootEntity<?> deleteUserById(Long id);

}
