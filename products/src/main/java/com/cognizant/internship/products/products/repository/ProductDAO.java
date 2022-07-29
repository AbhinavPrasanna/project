package com.cognizant.internship.products.products.repository;

import com.cognizant.internship.products.products.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductDAO extends MongoRepository<Product, String> {


}
