package com.cognizant.internship.products.products.services;
import com.cognizant.internship.products.products.models.Product;
import com.cognizant.internship.products.products.models.ProductDTO;
import com.cognizant.internship.products.products.repository.ProductDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductDAO productDAO;

    private final List<Product> products = new ArrayList<>();

    private Product product1;
    private ProductDTO productDTO1;
    private List<String> ids;

    @BeforeEach
    void setUp() {
        product1 = new Product("1", "1", "1", "1", "1","1");
        Product product2 = new Product("2", "2", "2", "2", "2","1");

        productDTO1 = new ProductDTO("1", "1", "1", "1", "1", "1");

        products.add(product1);
        products.add(product2);

        ids = new ArrayList<>();
        ids.add("1");
    }

    @Test
    void testGetAllProducts() {
        when(productDAO.findAll()).thenReturn(products);
        assertEquals(2, productService.getAllProducts().size());
    }

    @Test
    void testGetAllProductsByIds() {
        when(productDAO.findById("1")).thenReturn(Optional.of(product1));
        assertEquals(1, productService.getProductsByIds(ids).size());
    }

    @Test
    void testEmptyTable() {
        assertEquals(productDAO.findAll(), productService.getAllProducts());
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();

        when(productDAO.save(new Product())).thenReturn(product);
        assertEquals(productService.createProduct(new ProductDTO()), product);
    }

    @Test
    void testCreateProductInvalid() {
        when(productDAO.findById("1")).thenReturn(Optional.of(product1));
        assertNull(productService.createProduct(productDTO1));
    }

    @Test
    void testUpdateProduct() {
        when(productDAO.findById("1")).thenReturn(Optional.of(product1));
        assertEquals(productService.updateProduct(productDTO1), product1);
    }

    @Test
    void testUpdateProductInvalid() {
        when(productDAO.findById("1")).thenReturn(Optional.empty());
        assertNull(productService.updateProduct(productDTO1));
    }

    @Test
    void testFindById() {
        Product product = new Product();
        when(productDAO.findById("id")).thenReturn(Optional.of(product));
        assertEquals(productService.getProductById("id"), product);
    }


    @Test
    void testFindByIdNull() {
        when(productDAO.findById("id")).thenReturn(Optional.empty());
        assertNull(productService.getProductById("id"));
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        when(productDAO.findById("id")).thenReturn(Optional.of(product));
        assertTrue(productService.deleteProduct("id"));
    }
    @Test
    void testDeleteProductInvalid() {
        when(productDAO.findById("id")).thenReturn(Optional.empty());
        assertFalse(productService.deleteProduct("id"));
    }
}