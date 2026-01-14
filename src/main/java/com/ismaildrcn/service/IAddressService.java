package com.ismaildrcn.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ismaildrcn.model.dto.DtoAddressRequest;
import com.ismaildrcn.model.dto.DtoAddressResponse;

@Service
public interface IAddressService {

    public List<DtoAddressResponse> findAllAddressByUniqueId(UUID uniqueId);

    public DtoAddressResponse saveAddressByUniqueId(UUID uniqueId, DtoAddressRequest dtoAddressRequest);

}
