package com.ismaildrcn.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.IRestUserController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.DtoUserRequest;
import com.ismaildrcn.model.entity.User;
import com.ismaildrcn.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/user")
public class RestUserControllerImpl extends RestBaseController implements IRestUserController {

    @Autowired
    private IUserService userService;

    @Override
    @DeleteMapping("/me")
    public RootEntity<?> deleteUser(@AuthenticationPrincipal User user) {
        userService.deleteUser(user);
        return ok("User deleted successfully.");
    }

    @Override
    @GetMapping("/me")
    public RootEntity<?> getUser(@AuthenticationPrincipal User user) {
        return ok(userService.getUser(user));
    }

    @Override
    @PatchMapping("/me")
    public RootEntity<?> updateUser(@AuthenticationPrincipal User user,
            @Valid @RequestBody DtoUserRequest dtoUserRequest) {
        return ok(userService.updateUser(user, dtoUserRequest));
    }

}
