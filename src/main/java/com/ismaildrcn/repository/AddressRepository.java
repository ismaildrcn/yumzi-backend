package com.ismaildrcn.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

        @Query("SELECT a FROM Address a WHERE a.user.uniqueId = :uniqueId AND a.deletedAt IS NULL ORDER BY a.isDefault DESC")
        List<Address> findAllAddressByUniqueId(UUID uniqueId);

        @Query("SELECT a FROM Address a WHERE a.uniqueId = :uniqueId AND a.deletedAt IS NULL")
        Optional<Address> findByUniqueId(UUID uniqueId);

        @Modifying
        @Query("""
                            UPDATE Address a
                            SET a.isDefault = false
                            WHERE a.user.uniqueId = :userId
                              AND a.uniqueId <> :addressId
                        """)
        void unsetOtherDefaults(
                        @Param("userId") UUID userId,
                        @Param("addressId") UUID addressId);

}
