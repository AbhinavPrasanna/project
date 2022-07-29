package com.cognizant.internship.products.products.controllers;

import com.cognizant.internship.products.products.models.Product;
import com.cognizant.internship.products.products.models.ProductDTO;
import com.cognizant.internship.products.products.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> parties = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(parties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String id){
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
         Product product = productService.createProduct(productDTO);
        if(product != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.updateProduct(productDTO);
        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") String product_id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(product_id));
    }

    @PostMapping("/cart")
    public ResponseEntity<List<Product>> getProductsByIDs(@RequestBody List<String> ids){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByIds(ids));
    }
}
