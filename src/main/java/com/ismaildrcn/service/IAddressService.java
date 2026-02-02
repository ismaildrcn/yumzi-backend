package com.ismaildrcn.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ismaildrcn.model.dto.DtoAddressRequest;
import com.ismaildrcn.model.dto.DtoAddressResponse;
import com.ismaildrcn.model.entity.User;

@Service
public interface IAddressService {

    public List<DtoAddressResponse> findAllAddressByUser(User user);

    public DtoAddressResponse saveAddressByUser(User user, DtoAddressRequest dtoAddressRequest);

    public DtoAddressResponse updateAddressByUniqueId(UUID uniqueId, DtoAddressRequest dtoAddressRequest);

    public void deleteAddressByUniqueId(UUID uniqueId);

}
