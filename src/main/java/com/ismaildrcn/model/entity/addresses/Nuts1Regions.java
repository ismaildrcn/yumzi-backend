package com.ismaildrcn.model.entity.addresses;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nuts1_regions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nuts1Regions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name_tr", nullable = false)
    private String nameTr;

    @Column(name = "name_en", nullable = false)
    private String nameEn;

}
