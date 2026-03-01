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
    public void deleteUser(User user) {
        User existingUser = getUserEntityById(user.getUniqueId());

        existingUser.setDeletedAt(LocalDateTime.now());
        userRepository.save(existingUser);
    }

    @Override
    public DtoUserResponse getUser(User user) {
        DtoUserResponse dtoUser = new DtoUserResponse();
        List<DtoAddressResponse> dtoAddress = new ArrayList<>();

        User existingUser = getUserEntityById(user.getUniqueId());
        BeanUtils.copyProperties(existingUser, dtoUser);

        for (var address : existingUser.getAddress()) {
            DtoAddressResponse dtoAddressResponse = new DtoAddressResponse();
            BeanUtils.copyProperties(address, dtoAddressResponse);
            dtoAddress.add(dtoAddressResponse);
        }
        dtoUser.setAddress(dtoAddress);
        return dtoUser;
    }

    @Override
    public DtoUserResponse updateUser(User user, DtoUserRequest dtoUserRequest) {
        return updateUserFromDto(user, dtoUserRequest);
    }

    private DtoUserResponse updateUserFromDto(User user, DtoUserRequest dtoUserRequest) {
        DtoUserResponse dtoUser = new DtoUserResponse();
        User existingUser = getUserEntityById(user.getUniqueId());

        if (existingUser.getEmail() != null && !existingUser.getEmail().equals(dtoUserRequest.getEmail())) {
            existingUser.setEmailVerified(false);
        }

        BeanUtils.copyProperties(dtoUserRequest, existingUser);
        User updatedUser = userRepository.save(existingUser);
        BeanUtils.copyProperties(updatedUser, dtoUser);

        return dtoUser;
    }

    private User getUserEntityById(UUID uniqueId) {
        User user = userRepository.findByUniqueId(uniqueId)
                .orElseThrow(
                        () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND, "User Id: " + uniqueId)));
        return user;
    }

}
