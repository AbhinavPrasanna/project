package com.cognizant.internship.cart.cart.service;

import com.cognizant.internship.cart.cart.model.Cart;
import com.cognizant.internship.cart.cart.model.ProductDTO;
import com.cognizant.internship.cart.cart.repo.CartDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    @Autowired
    private CartDAO cartDAO;

    /**
     * Returns all carts stored in DB.
     * @return All carts
     */
    public List<Cart> getAllCarts() {
        return cartDAO.findAll();
    }

    /**
     * Returns a specific cart's contents.
     * @return Returns the first cart
     */
    public Cart getFirstCart() {
        try {
            return cartDAO.findAll().get(0);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Returns a specific cart's contents.
     * @param id Cart's ID
     * @return Returns a specific cart
     */
    public Cart getCart(String id) {
        Optional<Cart> cartOptional = cartDAO.findById(id);
        return cartOptional.orElse(null);
    }

    /**
     * Creates an empty cart and generates a Cart ID.
     * @return Cart Created
     */
    public Cart createCart() {
        try {
            return cartDAO.save(new Cart());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Adds new items to a cart.
     * @param cartId Cart's ID
     * @param product The new product being added as well as its quantity
     * @return Updated cart
     */
    public Cart addProductToCart(String cartId, ProductDTO product) {
        Optional<Cart> cartOptional = cartDAO.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            List<ProductDTO> productDTOS = cart.getProductList();

            for (ProductDTO currProduct : productDTOS) {                                        // Checks if the product already in cart
                if (currProduct.getProduct_id().equals(product.getProduct_id())) {              // Compares id with the current item
                    currProduct.setQuantity(currProduct.getQuantity() + product.getQuantity()); // Updates quantity of the existing item

                    cart.setProductList(productDTOS);
                    return cartDAO.save(cart);
                }
            }

            productDTOS.add(product);
            cart.setProductList(productDTOS);

            return cartDAO.save(cart);
        }

        return null;
    }

    /**
     * Deletes some or all of a product from a cart.
     * @param cartId Cart's ID
     * @param product Product and quantity to remove
     * @return Updated cart
     */
    public Cart removeProductFromCart(String cartId, ProductDTO product) {
        Optional<Cart> cartOptional = cartDAO.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            List<ProductDTO> productDTOS = cart.getProductList();

            int curr = 0;
            for (; curr < productDTOS.size(); curr++) {
                if (productDTOS.get(curr).getProduct_id().equals(product.getProduct_id())) {                            // Checks if the item already exists
                    if (productDTOS.get(curr).getQuantity() - product.getQuantity() > 0){                               // Compares the id with the current item
                        productDTOS.get(curr).setQuantity(productDTOS.get(curr).getQuantity() - product.getQuantity()); // Updates new quantity
                    }
                    else {                                                                                              // If the quantity of a product goes to 0
                        productDTOS.remove(curr);                                                                       // Removes item from cart
                    }

                    cart.setProductList(productDTOS);
                    return cartDAO.save(cart);
                }
            }
        }

        return null;
    }
}
