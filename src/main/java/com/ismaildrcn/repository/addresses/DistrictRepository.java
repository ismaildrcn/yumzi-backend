package com.ismaildrcn.repository.addresses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.addresses.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    List<District> findByProvinceName(String provinceName);
}
