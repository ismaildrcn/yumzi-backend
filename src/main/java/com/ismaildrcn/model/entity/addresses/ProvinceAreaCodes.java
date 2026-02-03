package com.ismaildrcn.model.entity.addresses;

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
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "province_area_codes", uniqueConstraints = @UniqueConstraint(columnNames = { "province_id",
        "area_code" }), indexes = @Index(name = "idx_area_codes_province", columnList = "province_id"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceAreaCodes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @Column(name = "area_code", nullable = false)
    private Integer areaCode;
}
