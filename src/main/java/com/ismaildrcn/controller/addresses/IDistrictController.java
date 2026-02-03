package com.ismaildrcn.controller.addresses;

import java.util.List;

import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.addresses.DtoDistrictResponse;

public interface IDistrictController {

    RootEntity<List<DtoDistrictResponse>> fetchDistrictsByProvince(String province);

}
