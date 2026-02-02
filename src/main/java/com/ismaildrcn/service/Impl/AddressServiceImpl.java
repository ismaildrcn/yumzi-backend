package com.ismaildrcn.service.Impl;

import java.time.LocalDateTime;
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

import jakarta.transaction.Transactional;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<DtoAddressResponse> findAllAddressByUser(User user) {
        List<DtoAddressResponse> dtoAddressResponses = new ArrayList<>();
        List<Address> allAddressFromDb = addressRepository.findAllAddressById(user.getId());

        for (Address address : allAddressFromDb) {
            DtoAddressResponse dtoAddressResponse = new DtoAddressResponse();
            BeanUtils.copyProperties(address, dtoAddressResponse);
            dtoAddressResponses.add(dtoAddressResponse);
        }
        return dtoAddressResponses;
    }

    @Override
    @Transactional
    public DtoAddressResponse saveAddressByUser(User user, DtoAddressRequest dtoAddressRequest) {
        DtoAddressResponse dtoAddressResponse = new DtoAddressResponse();
        User userDbObject = userRepository.findByUniqueId(user.getUniqueId()).orElseThrow(
                () -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_FOUND, "User Id: " + user.getUniqueId())));

        if (Boolean.TRUE.equals(dtoAddressRequest.isDefault())) {
            addressRepository.unsetOtherDefaults(userDbObject.getUniqueId(), user.getUniqueId());
        }

        Address address = createAddressFromDto(dtoAddressRequest);

        address.setUser(userDbObject);

        Address savedAddress = addressRepository.save(address);

        BeanUtils.copyProperties(savedAddress, dtoAddressResponse);
        return dtoAddressResponse;

    }

    @Override
    @Transactional
    public DtoAddressResponse updateAddressByUniqueId(UUID uniqueId, DtoAddressRequest dtoAddressRequest) {
        Address address = getAddressEntityByUniqueId(uniqueId);
        if (Boolean.TRUE.equals(address.isDefault()) || Boolean.TRUE.equals(dtoAddressRequest.isDefault())) {
            addressRepository.unsetOtherDefaults(address.getUser().getUniqueId(), address.getUniqueId());
        }
        BeanUtils.copyProperties(dtoAddressRequest, address);
        Address updatedAddress = addressRepository.save(address);

        DtoAddressResponse dtoAddressResponse = new DtoAddressResponse();
        BeanUtils.copyProperties(updatedAddress, dtoAddressResponse);

        return dtoAddressResponse;
    }

    @Override
    public void deleteAddressByUniqueId(UUID uniqueId) {
        Address address = getAddressEntityByUniqueId(uniqueId);
        address.setDeletedAt(LocalDateTime.now());
        addressRepository.save(address);
    }

    private Address getAddressEntityByUniqueId(UUID uniqueId) {
        return addressRepository.findByUniqueId(uniqueId).orElseThrow(() -> new BaseException(
                new ErrorMessage(MessageType.NO_RECORD_FOUND, "Address Id: " + uniqueId)));
    }

    private Address createAddressFromDto(DtoAddressRequest dtoAddressRequest) {
        Address address = new Address();
        BeanUtils.copyProperties(dtoAddressRequest, address);
        return address;
    }

}
