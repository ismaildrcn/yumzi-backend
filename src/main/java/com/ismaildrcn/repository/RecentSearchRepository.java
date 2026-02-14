package com.ismaildrcn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.RecentSearch;

@Repository
public interface RecentSearchRepository extends JpaRepository<RecentSearch, Long> {

    RecentSearch findByKeywordAndUserId(String keyword, Long userId);

    // Autocomplete - Keyword başlangıcına göre arama (önerilen - daha performanslı)
    // "piz" -> "pizza", "pizma" gibi sonuçlar döner
    List<RecentSearch> findByKeywordStartingWithIgnoreCase(String keyword);

    // Autocomplete - Keyword herhangi bir yerinde geçen sonuçlar
    // "piz" -> "pizza", "spizz", "pide-pizza" gibi sonuçlar döner
    List<RecentSearch> findByKeywordContainingIgnoreCase(String keyword);

    // Belirli bir kullanıcının autocomplete araması (başlangıca göre)
    List<RecentSearch> findByKeywordStartingWithIgnoreCaseAndUserId(String keyword, Long userId);

    // Belirli bir kullanıcının autocomplete araması (içerik göre)
    List<RecentSearch> findByKeywordContainingIgnoreCaseAndUserId(String keyword, Long userId);

    // Custom query ile - arama sayısına göre sıralı autocomplete
    // En çok aranan keyword'ler önce gelir
    @Query("SELECT rs FROM RecentSearch rs WHERE LOWER(rs.keyword) LIKE LOWER(CONCAT(:keyword, '%')) ORDER BY rs.searchCount DESC")
    List<RecentSearch> findAutocompleteKeywordsSortedBySearchCount(@Param("keyword") String keyword);

    // Kullanıcıya özel ve arama sayısına göre sıralı
    @Query("SELECT rs FROM RecentSearch rs WHERE rs.user.id = :userId AND LOWER(rs.keyword) LIKE LOWER(CONCAT(:keyword, '%')) ORDER BY rs.searchCount DESC")
    List<RecentSearch> findAutocompleteKeywordsByUserSortedBySearchCount(@Param("keyword") String keyword,
            @Param("userId") Long userId);

    // Limit ile sonuç sayısını sınırlama (en iyi 5 sonuç gibi)
    @Query("SELECT rs FROM RecentSearch rs WHERE LOWER(rs.keyword) LIKE LOWER(CONCAT(:keyword, '%')) ORDER BY rs.searchCount DESC LIMIT :limit")
    List<RecentSearch> findTopAutocompleteKeywords(@Param("keyword") String keyword, @Param("limit") int limit);

    @Query("SELECT rs FROM RecentSearch rs WHERE rs.user.id = :userId ORDER BY rs.searchCount DESC")
    List<RecentSearch> findTopSearchKeywordsByUser(@Param("userId") Long userId, @Param("limit") int limit);
}
