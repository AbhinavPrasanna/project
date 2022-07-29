package com.cognizant.internship.products.products.controllers;
import com.cognizant.internship.products.products.models.Product;
import com.cognizant.internship.products.products.models.ProductDTO;
import com.cognizant.internship.products.products.repository.ProductDAO;
import com.cognizant.internship.products.products.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private List<Product> products = new ArrayList<>();

    private Product product1;
    private Product product2;

    private ProductDTO productDTO1;

    @BeforeEach
    void setUp() {
        product1 = new Product("1", "1", "1", "1", "1", "1");
        product2 = new Product("2", "2", "2", "2", "2", "2");

        productDTO1 = new ProductDTO("1", "1", "1", "1", "1", "1");

        products.add(product1);
        products.add(product2);
    }

    @Test
    void testGetAllProducts() {
        when(productService.getAllProducts()).thenReturn(products);
        assertEquals(HttpStatus.OK, productController.getAllProducts().getStatusCode());

    }

    @Test
    void testGetProduct() {
        String id = "1";
        when(productService.getProductById(id)).thenReturn(product1);
        assertEquals(HttpStatus.OK, productController.getProductById(id).getStatusCode());
    }

    @Test
    void testGetProductsByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("2");

        when(productService.getProductsByIds(ids)).thenReturn(products);
        assertEquals(HttpStatus.OK, productController.getProductsByIDs(ids).getStatusCode());
    }

    @Test
    void testGetProductNotInDatabase() {
        String id = "id";
        when(productService.getProductById(id)).thenReturn(null);
        assertEquals(HttpStatus.NO_CONTENT, productController.getProductById(id).getStatusCode());
    }

    @Test
    void testCreateProduct() {
        ProductDTO p = new ProductDTO();
        when(productService.createProduct(p)).thenReturn(new Product());
        assertEquals(HttpStatus.CREATED, productController.createProduct(p).getStatusCode());
    }

    @Test
    void testCreateInvalidProduct() {
        when(productService.createProduct(productDTO1)).thenReturn(null);
        assertEquals(HttpStatus.BAD_REQUEST, productController.createProduct(productDTO1).getStatusCode());
    }

    @Test
    void testUpdateInvalidProduct() {
        ProductDTO p = new ProductDTO();
        when(productService.updateProduct(p)).thenReturn(null);
        ProductDTO productDTO = new ProductDTO();
        assertEquals(HttpStatus.BAD_REQUEST, productController.updateProduct(productDTO).getStatusCode());
    }

    @Test
    void testUpdateProduct() {
        when(productService.updateProduct(productDTO1)).thenReturn(product1);
        assertEquals(HttpStatus.OK, productController.updateProduct(productDTO1).getStatusCode());
    }

    @Test
    void testDeleteProduct() {
        when(productService.deleteProduct("1")).thenReturn(true);
        assertEquals(HttpStatus.OK, productController.deleteProduct("1").getStatusCode());
    }

    @Test
    void testDeleteInvalidProduct() {
        when(productService.deleteProduct("id")).thenReturn(false);
        assertEquals(HttpStatus.OK, productController.deleteProduct("id").getStatusCode());
    }
}
