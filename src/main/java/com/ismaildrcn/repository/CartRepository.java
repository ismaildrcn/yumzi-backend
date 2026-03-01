package com.ismaildrcn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {

}
