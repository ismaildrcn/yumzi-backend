package com.ismaildrcn.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByUniqueId(UUID uniqueId);

    boolean existsByName(String name);

    boolean existsBySlug(String slug);

    Optional<Restaurant> findBySlug(String slug);

    Optional<Restaurant> findByName(String name);

    @Query("SELECT r FROM Restaurant r JOIN r.category c WHERE c.uniqueId = :categoryId")
    List<Restaurant> findRestaurantByCategoryId(UUID categoryId);

}
