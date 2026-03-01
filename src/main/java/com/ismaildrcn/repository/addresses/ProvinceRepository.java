package com.ismaildrcn.repository.addresses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.addresses.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query("SELECT p FROM Province p ORDER BY p.name")
    List<Province> fetchAllProvince();

}
