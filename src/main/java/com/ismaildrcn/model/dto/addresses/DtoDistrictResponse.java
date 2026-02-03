package com.ismaildrcn.model.dto.addresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoDistrictResponse {

    private String name;

    private Integer population;

    private Integer area;

}
