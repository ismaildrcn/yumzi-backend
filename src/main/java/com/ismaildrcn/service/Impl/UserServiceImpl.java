package com.ismaildrcn.service.Impl;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.exception.BaseException;
import com.ismaildrcn.exception.ErrorMessage;
import com.ismaildrcn.exception.MessageType;
import com.ismaildrcn.model.dto.DtoUserRequest;
import com.ismaildrcn.model.dto.DtoUserResponse;
import com.ismaildrcn.model.entity.User;
import com.ismaildrcn.repository.UserRepository;
import com.ismaildrcn.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteUserById(Long id) {
        User user = getUserEntityById(id);

        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public DtoUserResponse getUserById(Long id) {
        DtoUserResponse dtoUser = new DtoUserResponse();
        User user = getUserEntityById(id);

        BeanUtils.copyProperties(user, dtoUser);
        return dtoUser;
    }

    @Override
    public DtoUserResponse updateUserById(Long id, DtoUserRequest dtoUserRequest) {
        return updateUserFromDto(id, dtoUserRequest);
    }

    private DtoUserResponse updateUserFromDto(Long id, DtoUserRequest dtoUserRequest) {
        DtoUserResponse dtoUser = new DtoUserResponse();
        User user = getUserEntityById(id);

        if (user.getEmail() != null && !user.getEmail().equals(dtoUserRequest.getEmail())) {
            user.setEmailVerified(false);
        }

        BeanUtils.copyProperties(dtoUserRequest, user);
        User updatedUser = userRepository.save(user);

        BeanUtils.copyProperties(updatedUser, dtoUser);

        return dtoUser;
    }

    private User getUserEntityById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND, "User Id: " + id)));
        if (user.getDeletedAt() != null) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND, "User Id: " + id));
        }
        return user;
    }

}
