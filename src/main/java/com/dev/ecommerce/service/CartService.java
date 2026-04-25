package com.dev.ecommerce.service;

import com.dev.ecommerce.entity.Cart;
import com.dev.ecommerce.enums.CartStatus;
import com.dev.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart> findActiveCartByUserId(Long userId) {
        return cartRepository.findCartByUserIdAndStatus(userId, CartStatus.ACTIVE);
    }
}
