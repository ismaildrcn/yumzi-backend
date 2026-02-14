package com.ismaildrcn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.RecentSearch;

@Repository
public interface RecentSearchRepository extends JpaRepository<RecentSearch, Long> {

    RecentSearch findByKeywordAndUserId(String keyword, Long userId);

}
