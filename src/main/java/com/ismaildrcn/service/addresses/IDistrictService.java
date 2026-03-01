package com.ismaildrcn.service.addresses;

import java.util.List;

import com.ismaildrcn.model.dto.addresses.DtoDistrictResponse;

public interface IDistrictService {

    List<DtoDistrictResponse> fetchDistrictsByProvince(String province);

}
