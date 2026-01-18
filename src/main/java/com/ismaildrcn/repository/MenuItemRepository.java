package com.ismaildrcn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    boolean existsByName(String name);

    boolean existsBySlug(String slug);

}
