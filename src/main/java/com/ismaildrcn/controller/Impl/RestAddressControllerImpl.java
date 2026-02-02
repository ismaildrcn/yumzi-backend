package com.ismaildrcn.controller.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.IRestAddressController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.DtoAddressRequest;
import com.ismaildrcn.model.dto.DtoAddressResponse;
import com.ismaildrcn.model.entity.User;
import com.ismaildrcn.service.IAddressService;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {

    @Autowired
    private IAddressService addressService;

    @Override
    @GetMapping("/list")
    public RootEntity<List<DtoAddressResponse>> findAllAddressByUniqueId(@AuthenticationPrincipal User user) {
        return ok(addressService.findAllAddressByUser(user));
    }

    @Override
    @PostMapping("/save")
    public RootEntity<DtoAddressResponse> saveAddressByUniqueId(
            @AuthenticationPrincipal User user,
            @RequestBody DtoAddressRequest dtoAddressRequest) {
        return ok(addressService.saveAddressByUser(user, dtoAddressRequest));
    }

    @Override
    @PatchMapping("/update/{uniqueAddressId}")
    public RootEntity<DtoAddressResponse> updateAddressByUniqueId(@PathVariable UUID uniqueAddressId,
            @RequestBody DtoAddressRequest dtoAddressRequest) {
        return ok(addressService.updateAddressByUniqueId(uniqueAddressId, dtoAddressRequest));
    }

    @Override
    @DeleteMapping("/delete/{uniqueId}")
    public RootEntity<String> deleteAddressByUniqueId(@PathVariable UUID uniqueId) {
        addressService.deleteAddressByUniqueId(uniqueId);
        return ok("Deletion successful.");
    }

}
