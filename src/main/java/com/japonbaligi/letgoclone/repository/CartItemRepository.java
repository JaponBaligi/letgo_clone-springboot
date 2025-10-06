package com.japonbaligi.letgoclone.repository;

import com.japonbaligi.letgoclone.entity.Cart;
import com.japonbaligi.letgoclone.entity.CartItem;
import com.japonbaligi.letgoclone.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    void deleteByCartAndProduct(Cart cart, Product product);

    List<CartItem> findByCart(Cart cart);

    @Query("SELECT ci FROM CartItem ci JOIN FETCH ci.product WHERE ci.cart = :cart")
    List<CartItem> findByCartWithProduct(@Param("cart") Cart cart);
}
