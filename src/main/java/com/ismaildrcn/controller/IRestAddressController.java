package com.ismaildrcn.controller;

import java.util.List;
import java.util.UUID;

import com.ismaildrcn.model.dto.DtoAddressRequest;
import com.ismaildrcn.model.dto.DtoAddressResponse;

public interface IRestAddressController {

    public RootEntity<List<DtoAddressResponse>> findAllAddressByUniqueId(UUID uniqueId);

    public RootEntity<DtoAddressResponse> saveAddressByUniqueId(UUID uniqueId,
            DtoAddressRequest dtoAddressRequest);

    public RootEntity<DtoAddressResponse> updateAddressByUniqueId(UUID uniqueId,
            DtoAddressRequest dtoAddressRequest);

}
