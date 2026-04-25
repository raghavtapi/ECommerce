package com.dev.ecommerce.service;

import com.dev.ecommerce.dto.CartItemRequestDTO;
import com.dev.ecommerce.entity.Cart;
import com.dev.ecommerce.entity.CartItem;
import com.dev.ecommerce.entity.Product;
import com.dev.ecommerce.enums.CartStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartItemService {

    private final ProductService productService;

    private final CartService cartService;

    public CartItemService(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @Transactional
    public void addItemToCart(CartItemRequestDTO request) {

        Long userId = request.getUserId();
        Cart cart = cartService.findActiveCartByUserId(userId)
                               .orElseGet(() -> createNewCart(userId));

        Product product = productService.getProductById(request.getProductId());

        CartItem cartItem = findCartItem(cart, product);
        if (cartItem == null) {
            cartItem = createCartItem(cart, product, request.getQuantity());
            cart.addItem(cartItem);
        } else  {
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        }

        cartService.saveCart(cart);
    }

    private Cart createNewCart(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setStatus(CartStatus.ACTIVE);
        return cartService.saveCart(cart);
    }

    private CartItem findCartItem(Cart cart, Product product) {
        return  cart.getItems()
                    .stream()
                    .filter(item -> product.getId().equals(item.getProduct().getId()))
                    .findFirst()
                    .orElse(null);
    }

    private CartItem createCartItem(Cart  cart, Product product, Integer quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setPriceAtTime(product.getPrice());
        return cartItem;
    }
}
