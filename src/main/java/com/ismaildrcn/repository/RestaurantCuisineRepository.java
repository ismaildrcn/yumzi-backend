package com.ismaildrcn.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.RestaurantCuisine;

@Repository
public interface RestaurantCuisineRepository extends JpaRepository<RestaurantCuisine, Long> {
    @Query("SELECT MAX(r.sortOrder) FROM RestaurantCuisine r")
    Integer findMaxSortOrder();

    boolean existsBySlug(String slug);

    boolean existsByName(String name);

    Optional<RestaurantCuisine> findBySlug(String slug);

    Optional<RestaurantCuisine> findByName(String name);

    Optional<RestaurantCuisine> findByUniqueId(UUID uniqueId);

    List<RestaurantCuisine> findAll(Sort sort);

}
