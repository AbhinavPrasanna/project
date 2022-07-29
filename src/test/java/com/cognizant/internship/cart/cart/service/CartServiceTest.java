package com.cognizant.internship.cart.cart.service;

import com.cognizant.internship.cart.cart.model.Cart;
import com.cognizant.internship.cart.cart.model.ProductDTO;
import com.cognizant.internship.cart.cart.repo.CartDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartDAO cartDAO;

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

    @Test
    void getAllCarts() {
        when(cartDAO.findAll()).thenReturn(carts);
        assertEquals(2, cartService.getAllCarts().size());
    }

    @Test
    void checkIfServiceReturnsCart() {
        when(cartDAO.findById("id")).thenReturn(Optional.ofNullable(cart1));
        assertEquals(cartService.getCart("id"), cart1);
    }

    @Test
    void checkIfServiceReturnsNull() {
        when(cartDAO.findById("id")).thenReturn(Optional.empty());
        assertNull(cartService.getCart("id"));
    }

    @Test
    void createCart() {
        Cart cart = new Cart();

        when(cartDAO.save(new Cart())).thenReturn(cart);
        assertEquals(cartService.createCart(), cart);
    }

    @Test
    void createCartReturnsNull() {
        when(cartDAO.save(new Cart())).thenThrow(new IllegalArgumentException());
        assertNull(cartService.createCart());
    }

    @Test
    void addNewProductToCart() {
        Cart cart = new Cart();
        Cart updatedCart = new Cart();

        ProductDTO newProd = new ProductDTO();
        newProd.setProduct_id("prod");
        newProd.setQuantity(2);

        List<ProductDTO> tempList = updatedCart.getProductList();
        tempList.add(newProd);
        updatedCart.setProductList(tempList);

        when(cartDAO.findById("id")).thenReturn(Optional.of(cart));
        when(cartDAO.save(updatedCart)).thenReturn(updatedCart);

        assertEquals(cartService.addProductToCart("id", newProd), updatedCart);
    }

    @Test
    void addProductToCartFAILS() {
        Cart updatedCart = new Cart();

        ProductDTO newProd = new ProductDTO();
        newProd.setProduct_id("prod");
        newProd.setQuantity(2);

        List<ProductDTO> tempList = updatedCart.getProductList();
        tempList.add(newProd);
        updatedCart.setProductList(tempList);

        when(cartDAO.findById("id")).thenReturn(Optional.empty());

        assertNull(cartService.addProductToCart("id", newProd));
    }

    @Test
    void addExistingProductToCart() {
        Cart cart = new Cart();
        Cart updatedCart = new Cart();

        ProductDTO initProd = new ProductDTO();
        initProd.setProduct_id("prod");
        initProd.setQuantity(2);

        ProductDTO newProd = new ProductDTO();
        newProd.setProduct_id("prod");
        newProd.setQuantity(4);

        List<ProductDTO> tempList = cart.getProductList();
        tempList.add(initProd);
        cart.setProductList(tempList);

        tempList = updatedCart.getProductList();
        tempList.add(newProd);
        updatedCart.setProductList(tempList);

        when(cartDAO.findById("id")).thenReturn(Optional.of(cart));
        when(cartDAO.save(updatedCart)).thenReturn(updatedCart);

        assertEquals(cartService.addProductToCart("id", initProd), updatedCart);
    }

    @Test
    void removeProductFromCart() {
        Cart cart = new Cart();
        Cart updatedCart = new Cart();

        ProductDTO initProd = new ProductDTO();
        initProd.setProduct_id("prod");
        initProd.setQuantity(2);

        List<ProductDTO> tempList = cart.getProductList();
        tempList.add(initProd);
        cart.setProductList(tempList);

        when(cartDAO.findById("id")).thenReturn(Optional.of(cart));
        when(cartDAO.save(updatedCart)).thenReturn(updatedCart);

        assertEquals(cartService.removeProductFromCart("id", initProd), updatedCart);
    }

    @Test
    void removeExistingProductFromCart() {
        Cart cart = new Cart();
        Cart updatedCart = new Cart();

        ProductDTO initProd = new ProductDTO();
        initProd.setProduct_id("prod");
        initProd.setQuantity(4);

        ProductDTO newProd = new ProductDTO();
        newProd.setProduct_id("prod");
        newProd.setQuantity(2);

        List<ProductDTO> tempList = cart.getProductList();
        tempList.add(initProd);
        cart.setProductList(tempList);

        tempList = updatedCart.getProductList();
        tempList.add(newProd);
        updatedCart.setProductList(tempList);

        when(cartDAO.findById("id")).thenReturn(Optional.of(cart));
        when(cartDAO.save(updatedCart)).thenReturn(updatedCart);

        assertEquals(cartService.removeProductFromCart("id", newProd), updatedCart);
    }

    @Test
    void removeProductFromCartFAILS() {
        Cart cart = new Cart();

        ProductDTO initProd = new ProductDTO();
        initProd.setProduct_id("prod");
        initProd.setQuantity(2);

        List<ProductDTO> tempList = cart.getProductList();
        tempList.add(initProd);
        cart.setProductList(tempList);

        when(cartDAO.findById("id")).thenReturn(Optional.empty());

        assertNull(cartService.removeProductFromCart("id", initProd));
    }
}