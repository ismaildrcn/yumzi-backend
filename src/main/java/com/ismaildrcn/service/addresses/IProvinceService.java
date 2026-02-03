package com.ismaildrcn.service.addresses;

import java.util.List;

import com.ismaildrcn.model.dto.addresses.DtoProvinceResponse;

public interface IProvinceService {

    List<DtoProvinceResponse> fetchAllProvince();

}
