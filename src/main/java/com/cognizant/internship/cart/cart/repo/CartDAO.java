package com.cognizant.internship.cart.cart.repo;

import com.cognizant.internship.cart.cart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CartDAO extends MongoRepository<Cart, String> {
    @Query("{cart_id:?0}")
    Cart findCartByCartID(String cart_id);

    @Query(value = "{ 'productList.product_id' : ?0 }")
    List<Cart> findByProductID(String product_id);
}