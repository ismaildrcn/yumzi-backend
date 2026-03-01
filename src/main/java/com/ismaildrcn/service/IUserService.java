package com.ismaildrcn.service;

import com.ismaildrcn.model.dto.DtoUserRequest;
import com.ismaildrcn.model.dto.DtoUserResponse;
import com.ismaildrcn.model.entity.User;

public interface IUserService {

    public DtoUserResponse updateUser(User user, DtoUserRequest dtoUserRequest);

    public DtoUserResponse getUser(User user);

    public void deleteUser(User user);
}
