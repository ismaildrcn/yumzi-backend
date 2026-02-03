package com.ismaildrcn.controller.Impl.addresses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.addresses.IProvinceController;
import com.ismaildrcn.model.dto.addresses.DtoProvinceResponse;
import com.ismaildrcn.service.addresses.IProvinceService;

@RestController
@RequestMapping("/rest/api/addresses/")
public class ProvinceControllerImpl implements IProvinceController {

    @Autowired
    private IProvinceService provinceService;

    @Override
    @GetMapping("/provinces")
    public List<DtoProvinceResponse> fetchAllProvince() {
        return provinceService.fetchAllProvince();
    }

}
