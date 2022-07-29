package com.cognizant.internship.cart.cart.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("carts")
@Data
public class Cart {
    @Id
    private String id;

    private List<ProductDTO> productList;

    /**
     * Initializes a Cart object with products.
     * @param productDTOS List of products in a cart
     */
    public Cart (List<ProductDTO> productDTOS) {
        productList = productDTOS;
    }

    /**
     * Initializes an empty Cart
     */
    public Cart () {
        productList = new ArrayList<>();
    }
}
