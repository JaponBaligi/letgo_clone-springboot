package com.japonbaligi.letgoclone.repository;

import com.japonbaligi.letgoclone.entity.Cart;
import com.japonbaligi.letgoclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);

    Optional<Cart> findByUserId(Long userId);
}
