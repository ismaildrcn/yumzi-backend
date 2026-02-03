package com.ismaildrcn.service.Impl.addresses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.model.dto.addresses.DtoProvinceResponse;
import com.ismaildrcn.model.dto.addresses.DtoRegionsResponse;
import com.ismaildrcn.model.entity.addresses.Province;
import com.ismaildrcn.repository.addresses.ProvinceRepository;
import com.ismaildrcn.service.addresses.IProvinceService;

@Service
public class ProvinceServiceImpl implements IProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<DtoProvinceResponse> fetchAllProvince() {
        List<Province> dbProvinces = provinceRepository.fetchAllProvince();
        List<DtoProvinceResponse> response = new ArrayList<>();

        for (Province province : dbProvinces) {
            DtoProvinceResponse dtoProvince = new DtoProvinceResponse();
            DtoRegionsResponse dtoRegion = new DtoRegionsResponse();
            BeanUtils.copyProperties(province, dtoProvince);
            BeanUtils.copyProperties(province.getRegion(), dtoRegion);
            dtoProvince.setRegion(dtoRegion);
            response.add(dtoProvince);
        }
        return response;
    }

}
