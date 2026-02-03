package com.ismaildrcn.controller.Impl.addresses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.controller.addresses.IDistrictController;
import com.ismaildrcn.model.dto.addresses.DtoDistrictResponse;
import com.ismaildrcn.service.addresses.IDistrictService;

@RestController
@RequestMapping("/rest/api/addresses/")
public class DistrictControllerImpl extends RestBaseController implements IDistrictController {

    @Autowired
    private IDistrictService districtService;

    @Override
    @GetMapping("/districts/{province}")
    public RootEntity<List<DtoDistrictResponse>> fetchDistrictsByProvince(@PathVariable String province) {
        return ok(districtService.fetchDistrictsByProvince(province));
    }

}
