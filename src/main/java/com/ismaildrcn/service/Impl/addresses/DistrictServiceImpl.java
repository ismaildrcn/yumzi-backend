package com.ismaildrcn.service.Impl.addresses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.model.dto.addresses.DtoDistrictResponse;
import com.ismaildrcn.model.entity.addresses.District;
import com.ismaildrcn.repository.addresses.DistrictRepository;
import com.ismaildrcn.service.addresses.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<DtoDistrictResponse> fetchDistrictsByProvince(String province) {
        List<District> dbDistricts = districtRepository.findByProvinceName(province);
        List<DtoDistrictResponse> response = new ArrayList<>();

        for (District district : dbDistricts) {
            DtoDistrictResponse dtoDistrict = new DtoDistrictResponse();
            BeanUtils.copyProperties(district, dtoDistrict);
            response.add(dtoDistrict);
        }
        return response;
    }

}
