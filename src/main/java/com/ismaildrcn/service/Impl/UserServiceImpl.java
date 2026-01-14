package com.ismaildrcn.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.exception.BaseException;
import com.ismaildrcn.exception.ErrorMessage;
import com.ismaildrcn.exception.MessageType;
import com.ismaildrcn.model.dto.DtoAddressResponse;
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
    public void deleteUserByUniqueId(UUID uniqueId) {
        User user = getUserEntityById(uniqueId);

        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public DtoUserResponse getUserByUniqueId(UUID uniqueId) {
        DtoUserResponse dtoUser = new DtoUserResponse();
        List<DtoAddressResponse> dtoAddress = new ArrayList<>();
        User user = getUserEntityById(uniqueId);
        BeanUtils.copyProperties(user, dtoUser);

        for (var address : user.getAddress()) {
            DtoAddressResponse dtoAddressResponse = new DtoAddressResponse();
            BeanUtils.copyProperties(address, dtoAddressResponse);
            dtoAddress.add(dtoAddressResponse);
        }
        dtoUser.setAddress(dtoAddress);
        return dtoUser;
    }

    @Override
    public DtoUserResponse updateUserByUniqueId(UUID uniqueId, DtoUserRequest dtoUserRequest) {
        return updateUserFromDto(uniqueId, dtoUserRequest);
    }

    private DtoUserResponse updateUserFromDto(UUID uniqueId, DtoUserRequest dtoUserRequest) {
        DtoUserResponse dtoUser = new DtoUserResponse();
        User user = getUserEntityById(uniqueId);

        if (user.getEmail() != null && !user.getEmail().equals(dtoUserRequest.getEmail())) {
            user.setEmailVerified(false);
        }

        BeanUtils.copyProperties(dtoUserRequest, user);
        User updatedUser = userRepository.save(user);

        BeanUtils.copyProperties(updatedUser, dtoUser);

        return dtoUser;
    }

    private User getUserEntityById(UUID uniqueId) {
        User user = userRepository.findByUniqueId(uniqueId)
                .orElseThrow(
                        () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND, "User Id: " + uniqueId)));
        if (user.getDeletedAt() != null) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND, "User Id: " + uniqueId));
        }
        return user;
    }

}
