package com.japonbaligi.letgoclone.repository;

import com.japonbaligi.letgoclone.entity.CheckoutInfo;
import com.japonbaligi.letgoclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckoutInfoRepository extends JpaRepository<CheckoutInfo, Long> {

    List<CheckoutInfo> findByUserOrderByIsDefaultDescCreatedAtDesc(User user);

    Optional<CheckoutInfo> findByUserAndIsDefaultTrue(User user);

    @Query("SELECT c FROM CheckoutInfo c WHERE c.user = :user ORDER BY c.isDefault DESC, c.createdAt DESC")
    List<CheckoutInfo> findUserCheckoutInfos(@Param("user") User user);

    void deleteByUser(User user);
}
