package com.japonbaligi.letgoclone.service;

import com.japonbaligi.letgoclone.entity.CheckoutInfo;
import com.japonbaligi.letgoclone.entity.User;
import com.japonbaligi.letgoclone.repository.CheckoutInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckoutService {

    @Autowired
    private CheckoutInfoRepository checkoutInfoRepository;

    public List<CheckoutInfo> getUserCheckoutInfos(User user) {
        if (user == null) {
            return List.of();
        }
        return checkoutInfoRepository.findUserCheckoutInfos(user);
    }

    public CheckoutInfo saveCheckoutInfo(CheckoutInfo checkoutInfo) {
        if (checkoutInfo == null || checkoutInfo.getUser() == null) {
            throw new IllegalArgumentException("CheckoutInfo and User cannot be null");
        }

        if (checkoutInfo.getIsDefault()) {
            List<CheckoutInfo> userInfos = checkoutInfoRepository
                    .findByUserOrderByIsDefaultDescCreatedAtDesc(checkoutInfo.getUser());
            userInfos.forEach(info -> {
                if (info.getIsDefault()) {
                    info.setIsDefault(false);
                    checkoutInfoRepository.save(info);
                }
            });
        }

        return checkoutInfoRepository.save(checkoutInfo);
    }

    public Optional<CheckoutInfo> getCheckoutInfoById(Long id, User user) {
        if (id == null || user == null) {
            return Optional.empty();
        }
        return checkoutInfoRepository.findById(id)
                .filter(info -> info.getUser().getId().equals(user.getId()));
    }

    public void deleteCheckoutInfo(Long id, User user) {
        if (id == null || user == null) {
            return;
        }
        Optional<CheckoutInfo> checkoutInfo = getCheckoutInfoById(id, user);
        if (checkoutInfo.isPresent()) {
            checkoutInfoRepository.delete(checkoutInfo.get());
        }
    }

    public Optional<CheckoutInfo> getDefaultCheckoutInfo(User user) {
        if (user == null) {
            return Optional.empty();
        }
        return checkoutInfoRepository.findByUserAndIsDefaultTrue(user);
    }

    public String generateReceiptId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder receiptId = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            receiptId.append(chars.charAt((int) (Math.random() * chars.length())));
        }
        return receiptId.toString();
    }
}
