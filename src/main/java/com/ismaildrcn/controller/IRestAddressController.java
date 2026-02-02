package com.ismaildrcn.controller;

import java.util.List;
import java.util.UUID;

import com.ismaildrcn.model.dto.DtoAddressRequest;
import com.ismaildrcn.model.dto.DtoAddressResponse;
import com.ismaildrcn.model.entity.User;

public interface IRestAddressController {

        public RootEntity<List<DtoAddressResponse>> findAllAddressByUniqueId(User user);

        public RootEntity<DtoAddressResponse> saveAddressByUniqueId(User user,
                        DtoAddressRequest dtoAddressRequest);

        public RootEntity<DtoAddressResponse> updateAddressByUniqueId(UUID uniqueAddressId,
                        DtoAddressRequest dtoAddressRequest);

        public RootEntity<String> deleteAddressByUniqueId(UUID uniqueId);

}
