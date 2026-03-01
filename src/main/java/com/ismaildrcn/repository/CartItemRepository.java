package com.ismaildrcn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ismaildrcn.model.entity.CartItem;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, String> {

}
