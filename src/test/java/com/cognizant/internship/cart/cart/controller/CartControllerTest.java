package com.cognizant.internship.cart.cart.controller;

import com.cognizant.internship.cart.cart.model.Cart;
import com.cognizant.internship.cart.cart.model.ProductDTO;
import com.cognizant.internship.cart.cart.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    @InjectMocks
    private CartController cartController;

    @Mock
    private CartService cartService;

    private List<Cart> carts = new ArrayList<>();

    private Cart cart1;
    private Cart cart2;

    private List<ProductDTO> productDTOs1 = new ArrayList<>();
    private List<ProductDTO> productDTOs2 = new ArrayList<>();

    private ProductDTO productDTO1 = new ProductDTO("prod1", 2);
    private ProductDTO productDTO2 = new ProductDTO("prod2", 1);

    @BeforeEach
    void setUp() {
        productDTOs1.add(productDTO1);
        productDTOs1.add(productDTO2);

        productDTOs2.add(productDTO2);
        productDTOs2.add(productDTO1);

        cart1 = new Cart(productDTOs1);
        cart2 = new Cart(productDTOs2);

        carts.add(cart1);
        carts.add(cart2);
    }

//    @Test
//    void getAllCarts() {
//        when(cartService.getAllCarts()).thenReturn(carts);
//        assertEquals(HttpStatus.OK, cartController.getAllCarts().getStatusCode());
//    }

    @Test
    void getFirstCart() {
        Cart cart = new Cart();
        when(cartService.getFirstCart()).thenReturn(cart);
        assertEquals(HttpStatus.OK, cartController.getFirstCart().getStatusCode());
    }

    @Test
    void getCart() {
        String id = "id";
        when(cartService.getCart(id)).thenReturn(cart1);
        assertEquals(HttpStatus.OK, cartController.getCart(id).getStatusCode());
    }

    @Test
    void getCartNotInDatabase() {
        String id = "id";
        when(cartService.getCart(id)).thenReturn(null);
        assertEquals(HttpStatus.NO_CONTENT, cartController.getCart(id).getStatusCode());
    }

    @Test
    void createCart() {
        when(cartService.createCart()).thenReturn(new Cart());
        assertEquals(HttpStatus.CREATED, cartController.createCart().getStatusCode());
    }

    @Test
    void createCartFAIL() {
        when(cartService.createCart()).thenReturn(null);
        assertEquals(HttpStatus.BAD_REQUEST, cartController.createCart().getStatusCode());
    }

    @Test
    void addNewProductToCart() {
        ProductDTO initialDTO = new ProductDTO();
        initialDTO.setQuantity(1);
        initialDTO.setProduct_id("prod");

        Cart updatedCart = new Cart();

        List<ProductDTO> tempList = updatedCart.getProductList();
        tempList.add(initialDTO);
        updatedCart.setProductList(tempList);

        when(cartService.addProductToCart("id", initialDTO)).thenReturn(updatedCart);

        ResponseEntity<Cart> entity = cartController.addToCart("id", initialDTO);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(1, Objects.requireNonNull(entity.getBody()).getProductList().size());
    }

    @Test
    void addNewProductToCartFAIL() {
        ProductDTO initialDTO = new ProductDTO();
        initialDTO.setQuantity(1);
        initialDTO.setProduct_id("prod");

        Cart updatedCart = new Cart();

        List<ProductDTO> tempList = updatedCart.getProductList();
        tempList.add(initialDTO);
        updatedCart.setProductList(tempList);

        when(cartService.addProductToCart("id", initialDTO)).thenReturn(null);

        ResponseEntity<Cart> entity = cartController.addToCart("id", initialDTO);

        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
    }

    @Test
    void removeProductFromCart() {
        ProductDTO initialDTO = new ProductDTO();
        initialDTO.setQuantity(1);
        initialDTO.setProduct_id("prod");

        Cart emptyCart = new Cart();
        Cart updatedCart = new Cart();

        List<ProductDTO> tempList = updatedCart.getProductList();
        tempList.add(initialDTO);
        updatedCart.setProductList(tempList);

        when(cartService.removeProductFromCart("id", initialDTO)).thenReturn(emptyCart);

        ResponseEntity<Cart> entity = cartController.removeFromCart("id", initialDTO.getProduct_id(),
                                                                    initialDTO.getQuantity());

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(0, Objects.requireNonNull(entity.getBody()).getProductList().size());
    }

    @Test
    void removeProductFromCartFAIL() {
        ProductDTO initialDTO = new ProductDTO();
        initialDTO.setQuantity(1);
        initialDTO.setProduct_id("prod");

        Cart updatedCart = new Cart();

        List<ProductDTO> tempList = updatedCart.getProductList();
        tempList.add(initialDTO);
        updatedCart.setProductList(tempList);

        when(cartService.removeProductFromCart("id", initialDTO)).thenReturn(null);

        ResponseEntity<Cart> entity = cartController.removeFromCart("id", initialDTO.getProduct_id(),
                                                                    initialDTO.getQuantity());

        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
    }
}