package com.ismaildrcn.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.user.userUniqueId = :userUniqueId")
    List<Address> findAllAddressByUserUniqueId(UUID userUniqueId);

}
