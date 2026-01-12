package com.ismaildrcn.service.Impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ismaildrcn.exception.BaseException;
import com.ismaildrcn.exception.ErrorMessage;
import com.ismaildrcn.exception.MessageType;
import com.ismaildrcn.jwt.JWTService;
import com.ismaildrcn.model.dto.AuthRequest;
import com.ismaildrcn.model.dto.AuthResponse;
import com.ismaildrcn.model.dto.DtoUserResponse;
import com.ismaildrcn.model.dto.RefreshTokenRequest;
import com.ismaildrcn.model.entity.RefreshToken;
import com.ismaildrcn.model.entity.User;
import com.ismaildrcn.repository.RefreshTokenRepository;
import com.ismaildrcn.repository.UserRepository;
import com.ismaildrcn.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public DtoUserResponse register(AuthRequest authRequest) {
        DtoUserResponse dtoUser = new DtoUserResponse();
        User dbUser = userRepository.save(createUserFromDto(authRequest));

        BeanUtils.copyProperties(dbUser, dtoUser);
        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(), authRequest.getPassword());

            authenticationProvider.authenticate(authToken);

            Optional<User> byEmail = userRepository.findByEmail(authRequest.getEmail());

            String accessToken = jwtService.generateToken(byEmail.get());
            RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(byEmail.get()));

            return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());

        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
        }
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshToken) {
        RefreshToken optRefreshToken = refreshTokenRepository
                .findByRefreshToken(refreshToken.getRefreshToken()).orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, refreshToken.getRefreshToken())));

        if (isTokenExpired(optRefreshToken)) {
            throw new BaseException(
                    new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, refreshToken.getRefreshToken()));
        }

        User user = optRefreshToken.getUser();
        String accessToken = jwtService.generateToken(user);
        RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));

        return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
    }

    private User createUserFromDto(AuthRequest authRequest) {
        User user = new User();
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        return user;
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiredDate(LocalDateTime.now().plusHours(4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        return refreshToken;
    }

    private boolean isTokenExpired(RefreshToken refreshToken) {
        return refreshToken.getExpiredDate().isBefore(LocalDateTime.now());
    }
}
