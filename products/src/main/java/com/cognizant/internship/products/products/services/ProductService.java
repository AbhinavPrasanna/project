package com.cognizant.internship.products.products.services;

import com.cognizant.internship.products.products.models.Product;
import com.cognizant.internship.products.products.models.ProductDTO;
import com.cognizant.internship.products.products.repository.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public List<Product> getAllProducts() {
        List<Product> products = productDAO.findAll();
        for (Product product : products) {
            product.setPicture_id("https://storage.googleapis.com/intern_iware_assets/glasses/2d_glasses/" + product.getPicture_id());
        }
        return products;
    }

    public Product getProductById(String product_id) {
        Optional<Product> id = productDAO.findById(product_id);
        if (id.isPresent()) {
            Product product = id.get();
            product.setPicture_id("https://storage.googleapis.com/intern_iware_assets/glasses/2d_glasses/" + product.getPicture_id());
            return product;
        }
        return null;
    }

    public Product createProduct(ProductDTO productDTO) {
        if(!productDAO.findById(productDTO.getProduct_id()).isPresent()) {
            Product p = new Product(productDTO);
            productDAO.save(p);
            return p;
        }
        return null;
    }

    public Boolean deleteProduct(String product_id) {
            Optional<Product> partyHost = productDAO.findById(product_id);
            if(partyHost.isPresent()) {
                productDAO.deleteById(product_id);
                return true;
            }
            return false;
    }

    public Product updateProduct(ProductDTO productDTO) {
        Optional<Product> productOptional = productDAO.findById(productDTO.getProduct_id());
        if(productOptional.isPresent()) {
            Product p = new Product(productDTO);
            productDAO.save(p);
            return productOptional.get();
        }
        return null;
    }

    public List<Product> getProductsByIds(List<String> product_id) {
        Optional<Product> id;
        List<Product> products = new ArrayList<>();
        for (String idString : product_id) {
            id = productDAO.findById(idString);
            if (id.isPresent()) {
                Product product = id.get();
                product.setPicture_id("https://storage.googleapis.com/intern_iware_assets/glasses/2d_glasses/" + product.getPicture_id());
                products.add(product);
            }
        }
        return products;
    }
}
