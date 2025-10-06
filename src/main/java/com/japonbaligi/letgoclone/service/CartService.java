package com.japonbaligi.letgoclone.service;

import com.japonbaligi.letgoclone.entity.*;
import com.japonbaligi.letgoclone.repository.CartItemRepository;
import com.japonbaligi.letgoclone.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart getOrCreateCart(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        Optional<Cart> existingCart = cartRepository.findByUser(user);
        if (existingCart.isPresent()) {
            return existingCart.get();
        }

        Cart newCart = new Cart(user);
        return cartRepository.save(newCart);
    }

    public void addToCart(User user, Product product, Integer quantity) {
        if (user == null || product == null || quantity == null || quantity <= 0) {
            return;
        }
        Cart cart = getOrCreateCart(user);

        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem(cart, product, quantity);
            cartItemRepository.save(newItem);
        }
    }

    public void removeFromCart(User user, Product product) {
        if (user == null || product == null) {
            return;
        }
        Cart cart = getOrCreateCart(user);
        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product);
        if (existingItem.isPresent()) {
            cartItemRepository.delete(existingItem.get());
        }
    }

    public void updateCartItemQuantity(User user, Product product, Integer quantity) {
        if (user == null || product == null || quantity == null) {
            return;
        }
        Cart cart = getOrCreateCart(user);
        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            if (quantity <= 0) {
                cartItemRepository.delete(item);
            } else {
                item.setQuantity(quantity);
                cartItemRepository.save(item);
            }
        }
    }

    public List<CartItem> getCartItems(User user) {
        if (user == null) {
            return List.of();
        }
        Cart cart = getOrCreateCart(user);
        return cartItemRepository.findByCartWithProduct(cart);
    }

    public int getCartItemCount(User user) {
        if (user == null) {
            return 0;
        }
        Cart cart = getOrCreateCart(user);
        List<CartItem> items = cartItemRepository.findByCartWithProduct(cart);
        return items.stream().mapToInt(CartItem::getQuantity).sum();
    }

    public double getCartTotal(User user) {
        if (user == null) {
            return 0.0;
        }
        Cart cart = getOrCreateCart(user);
        List<CartItem> items = cartItemRepository.findByCartWithProduct(cart);
        return items.stream()
                .filter(item -> item != null && item.getProduct() != null && item.getProduct().getPrice() != null)
                .mapToDouble(item -> item.getProduct().getPrice().doubleValue() * item.getQuantity())
                .sum();
    }

    public void clearCart(User user) {
        if (user == null) {
            return;
        }
        Cart cart = getOrCreateCart(user);
        List<CartItem> items = cartItemRepository.findByCartWithProduct(cart);
        if (!items.isEmpty()) {
            cartItemRepository.deleteAll(items);
        }
    }
}
