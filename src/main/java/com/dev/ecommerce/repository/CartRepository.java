package com.dev.ecommerce.repository;

import com.dev.ecommerce.entity.Cart;
import com.dev.ecommerce.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart getCartByUserIdAndStatus(Long userId, CartStatus status);
}
