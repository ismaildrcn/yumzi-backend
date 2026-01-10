package com.ismaildrcn.service.Impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ismaildrcn.model.dto.AuthRequest;
import com.ismaildrcn.model.dto.DtoUser;
import com.ismaildrcn.model.entity.User;
import com.ismaildrcn.repository.UserRepository;
import com.ismaildrcn.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private User createUserFromDto(AuthRequest authRequest) {
        User user = new User();
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        return user;
    }

    @Override
    public DtoUser register(AuthRequest authRequest) {
        DtoUser dtoUser = new DtoUser();
        User dbUser = userRepository.save(createUserFromDto(authRequest));

        BeanUtils.copyProperties(dbUser, dtoUser);
        return dtoUser;
    }

}
