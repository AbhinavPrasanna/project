package com.cognizant.internship.products.products.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products_3d")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    private String product_id;

    private String product_name;

    private String product_price;

    private String product_desc;

    private String picture_id;

    private String brand;

    public Product (ProductDTO productDTO) {
        product_id = productDTO.getProduct_id();
        product_name = productDTO.getProduct_name();
        product_price = productDTO.getProduct_price();
        product_desc = productDTO.getProduct_desc();
        picture_id = productDTO.getPicture_id();
        brand = productDTO.getBrand();
    }
}
