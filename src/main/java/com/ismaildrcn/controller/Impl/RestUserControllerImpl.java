package com.ismaildrcn.controller.Impl;

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
import com.ismaildrcn.model.dto.DtoUserIU;
import com.ismaildrcn.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/user")
public class RestUserControllerImpl extends RestBaseController implements IRestUserController {

    @Autowired
    private IUserService userService;

    @Override
    @DeleteMapping("/{id}")
    public RootEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ok("User deleted successfully.");
    }

    @Override
    @GetMapping("/{id}")
    public RootEntity<?> getUserById(@PathVariable Long id) {
        return ok(userService.getUserById(id));
    }

    @Override
    @PatchMapping("/{id}")
    public RootEntity<?> updateUserById(@PathVariable Long id, @Valid @RequestBody DtoUserIU dtoUserIU) {
        return ok(userService.updateUserById(id, dtoUserIU));
    }

}
