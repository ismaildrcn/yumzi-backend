package com.ismaildrcn.service;

import com.ismaildrcn.model.dto.DtoUser;
import com.ismaildrcn.model.dto.DtoUserIU;

public interface IUserService {

    public DtoUser updateUserById(Long id, DtoUserIU dtoUserIU);

    public DtoUser getUserById(Long id);

    public void deleteUserById(Long id);
}
