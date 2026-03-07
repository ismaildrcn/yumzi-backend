package com.ismaildrcn.repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.User;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUniqueId(UUID uniqueId);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.favoriteItems LEFT JOIN FETCH u.favoriteRestaurants WHERE u.id = :id")
    Optional<User> findUserWithFavorites(@Param("id") Long id);

    @Query("SELECT r.uniqueId FROM User u JOIN u.favoriteRestaurants r WHERE u.id = :userId")
    Set<UUID> findFavoriteRestaurantIds(@Param("userId") Long userId);

    @Query("SELECT m.uniqueId FROM User u JOIN u.favoriteItems m WHERE u.id = :userId")
    Set<UUID> findFavoriteMenuItemIds(@Param("userId") Long userId);

}
