package com.ismaildrcn.model.entity.addresses;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "provinces", indexes = {
        @Index(name = "idx_provinces_region", columnList = "region_id"),
        @Index(name = "idx_provinces_nuts2", columnList = "nuts2_code")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "population")
    private Integer population;

    @Column(name = "area")
    private Integer area;

    @Column(name = "altitude")
    private Integer altitude;

    @Column(name = "is_coastal")
    private Boolean isCoastal = false;

    @Column(name = "is_metropolitan")
    private Boolean isMetropolitan = false;

    @Column(name = "latitude", precision = 10, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 10, scale = 6)
    private BigDecimal longitude;

    @Column(name = "google_maps_url", length = 255)
    private String googleMapsUrl;

    @Column(name = "openstreetmap_url", length = 255)
    private String openstreetmapUrl;

    @Column(name = "nuts3_code", length = 10)
    private String nuts3Code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nuts2_code", referencedColumnName = "code")
    private Nuts2Regions nuts2Region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Regions region;

}
