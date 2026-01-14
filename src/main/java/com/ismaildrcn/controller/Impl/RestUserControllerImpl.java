package com.ismaildrcn.controller.Impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.IRestUserController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.DtoUserRequest;
import com.ismaildrcn.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/user")
public class RestUserControllerImpl extends RestBaseController implements IRestUserController {

    @Autowired
    private IUserService userService;

    @Override
    @DeleteMapping("/{uniqueId}")
    public RootEntity<?> deleteUserByUniqueId(@PathVariable UUID uniqueId) {
        userService.deleteUserByUniqueId(uniqueId);
        return ok("User deleted successfully.");
    }

    @Override
    @GetMapping("/{uniqueId}")
    public RootEntity<?> getUserByUniqueId(@PathVariable UUID uniqueId) {
        return ok(userService.getUserByUniqueId(uniqueId));
    }

    @Override
    @PatchMapping("/{uniqueId}")
    public RootEntity<?> updateUserByUniqueId(@PathVariable UUID uniqueId,
            @Valid @RequestBody DtoUserRequest dtoUserRequest) {
        return ok(userService.updateUserByUniqueId(uniqueId, dtoUserRequest));
    }

}
