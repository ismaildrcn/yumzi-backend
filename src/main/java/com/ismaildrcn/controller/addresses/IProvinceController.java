package com.ismaildrcn.controller.addresses;

import java.util.List;

import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.addresses.DtoProvinceResponse;

public interface IProvinceController {

    RootEntity<List<DtoProvinceResponse>> fetchAllProvince();

}
