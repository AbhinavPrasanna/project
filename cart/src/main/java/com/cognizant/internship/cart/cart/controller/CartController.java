package com.cognizant.internship.cart.cart.controller;

import com.cognizant.internship.cart.cart.model.Cart;
import com.cognizant.internship.cart.cart.model.ProductDTO;
import com.cognizant.internship.cart.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@Controller
@RequestMapping(value = "/cart")
@RequiredArgsConstructor
public class CartController {

    @Autowired
    CartService cartService;

//    /**
//     * Returns all carts stored in DB.
//     * @return List of all carts and an HTTP response status code
//     */
//    @GetMapping
//    public ResponseEntity<List<Cart>> getAllCarts() {
//        List<Cart> carts = cartService.getAllCarts();
//        return ResponseEntity.status(HttpStatus.OK).body(carts);
//    }

    /**
     * Returns first cart in DB
     * @return First cart and an HTTP response status code
     */
    @GetMapping
    public ResponseEntity<Cart> getFirstCart() {
        Cart cart = cartService.getFirstCart();
        if (cart != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Returns a specific cart's contents.
     * @param id Cart's ID
     * @return Returns a cart and an HTTP response status code
     */
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable("cartId") String id) {
        Cart cart = cartService.getCart(id);
        if (cart != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Creates an empty cart and generates a Cart ID.
     * @return Cart Created
     */
    @PostMapping
    public ResponseEntity<Cart> createCart() {
        Cart newCart = cartService.createCart();
        if (newCart != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newCart);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /**
     * Adds new items to a cart.
     * @param cartId Cart's ID
     * @param newProduct The new product being added as well as its quantity
     * @return Updated cart
     */
    @PutMapping("/{cartId}")
    public ResponseEntity<Cart> addToCart(@PathVariable("cartId") String cartId, @RequestBody ProductDTO newProduct) {
        Cart updatedCart = cartService.addProductToCart(cartId, newProduct);
        if (updatedCart != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedCart);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /**
     * Deletes some or all of a product from a cart.
     * @param cartId Cart's ID
     * @param productId Product to remove
     * @param quantity Quantity to remove
     * @return Updated cart
     */
    @DeleteMapping("/{cartId}/{productId}/{quantity}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable("cartId") String cartId,
                                               @PathVariable("productId") String productId,
                                               @PathVariable("quantity") Integer quantity) {
        ProductDTO productDTO = new ProductDTO(productId, quantity);
        Cart updatedCart = cartService.removeProductFromCart(cartId, productDTO);
        if (updatedCart != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedCart);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
