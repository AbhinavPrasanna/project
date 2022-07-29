package com.cognizant.internship.products.products.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    @Id
    private String product_id;

    private String product_name;

    private String product_price;

    private String product_desc;

    private String picture_id;

    private String brand;
}
