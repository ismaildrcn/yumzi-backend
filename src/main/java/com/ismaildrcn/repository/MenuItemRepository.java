package com.ismaildrcn.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    boolean existsByName(String name);

    boolean existsBySlug(String slug);

    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.uniqueId = :restaurantUniqueId")
    List<MenuItem> findByRestaurantUniqueId(UUID restaurantUniqueId);

}
