package com.ismaildrcn.controller;

import java.util.List;
import java.util.UUID;

import com.ismaildrcn.model.dto.DtoAddressRequest;
import com.ismaildrcn.model.dto.DtoAddressResponse;

public interface IRestAddressController {

    public RootEntity<List<DtoAddressResponse>> findAllAddressByUserUniqueId(UUID userUniqueId);

    public RootEntity<DtoAddressResponse> saveAddressByUserUniqueId(UUID userUniqueId,
            DtoAddressRequest dtoAddressRequest);

}
