package com.ismaildrcn.model.dto.addresses;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoProvinceResponse {

    private String name;

    private Integer population;

    private Integer area;

    private Integer altitude;

    private Boolean isCoastal = false;

    private Boolean isMetropolitan = false;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String googleMapsUrl;

    private String openstreetmapUrl;

    private DtoRegionsResponse region;

}
