package com.ismaildrcn.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.MenuCategory;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {

    Optional<MenuCategory> findByUniqueId(UUID uniqueId);

}
