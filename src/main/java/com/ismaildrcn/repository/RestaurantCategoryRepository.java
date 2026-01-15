package com.ismaildrcn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.RestaurantCategory;

@Repository
public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Long> {

    @Query("SELECT MAX(r.sortOrder) FROM RestaurantCategory r")
    Integer findMaxSortOrder();

}
