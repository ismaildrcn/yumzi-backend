package com.ismaildrcn.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.exception.BaseException;
import com.ismaildrcn.exception.ErrorMessage;
import com.ismaildrcn.exception.MessageType;
import com.ismaildrcn.model.dto.DtoAddressRequest;
import com.ismaildrcn.model.dto.DtoAddressResponse;
import com.ismaildrcn.model.entity.Address;
import com.ismaildrcn.model.entity.User;
import com.ismaildrcn.repository.AddressRepository;
import com.ismaildrcn.repository.UserRepository;
import com.ismaildrcn.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<DtoAddressResponse> findAllAddressByUserUniqueId(UUID userUniqueId) {
        List<DtoAddressResponse> dtoAddressResponses = new ArrayList<>();
        List<Address> allAddressFromDb = addressRepository.findAllAddressByUserUniqueId(userUniqueId);

        for (Address address : allAddressFromDb) {
            DtoAddressResponse dtoAddressResponse = new DtoAddressResponse();
            BeanUtils.copyProperties(address, dtoAddressResponse);
            dtoAddressResponses.add(dtoAddressResponse);
        }
        return dtoAddressResponses;
    }

    @Override
    public DtoAddressResponse saveAddressByUserUniqueId(UUID userUniqueId, DtoAddressRequest dtoAddressRequest) {
        DtoAddressResponse dtoAddressResponse = new DtoAddressResponse();
        User userDbObject = userRepository.findByUserUniqueId(userUniqueId).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND, "User Id: " + userUniqueId)));

        Address address = createAddressFromDto(dtoAddressRequest);

        address.setUser(userDbObject);

        Address savedAddress = addressRepository.save(address);

        BeanUtils.copyProperties(savedAddress, dtoAddressResponse);
        return dtoAddressResponse;

    }

    private Address createAddressFromDto(DtoAddressRequest dtoAddressRequest) {
        Address address = new Address();
        BeanUtils.copyProperties(dtoAddressRequest, address);
        return address;
    }

}
