package com.cognizant.internship.cart.cart;

import com.cognizant.internship.cart.cart.repo.CartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CartApplication implements CommandLineRunner {

    @Autowired
    CartDAO cartDAO;

    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }

    public void run(String... args) {
        // cartDAO.deleteAll();
        System.out.println("setup complete");
    }

}
