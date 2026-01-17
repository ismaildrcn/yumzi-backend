package com.ismaildrcn.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.RestaurantCategory;

@Repository
public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Long> {

    @Query("SELECT MAX(r.sortOrder) FROM RestaurantCategory r")
    Integer findMaxSortOrder();

    boolean existsBySlug(String slug);

    boolean existsByName(String name);

    Optional<RestaurantCategory> findByUniqueId(UUID uniqueId);

    Optional<RestaurantCategory> findBySlug(String slug);

    Optional<RestaurantCategory> findByName(String name);

}
