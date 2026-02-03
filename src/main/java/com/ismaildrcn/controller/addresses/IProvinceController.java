package com.ismaildrcn.controller.addresses;

import java.util.List;

import com.ismaildrcn.model.dto.addresses.DtoProvinceResponse;

public interface IProvinceController {

    List<DtoProvinceResponse> fetchAllProvince();

}
