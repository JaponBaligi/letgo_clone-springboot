package com.japonbaligi.letgoclone.controller;

import com.japonbaligi.letgoclone.entity.CheckoutInfo;
import com.japonbaligi.letgoclone.entity.User;
import com.japonbaligi.letgoclone.service.CartService;
import com.japonbaligi.letgoclone.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }

        // Check if cart is empty
        List<com.japonbaligi.letgoclone.entity.CartItem> cartItems = cartService.getCartItems(currentUser);
        if (cartItems.isEmpty()) {
            return "redirect:/cart";
        }

        model.addAttribute("title", "Ödeme - Takı Kazan");
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", cartService.getCartTotal(currentUser));
        model.addAttribute("cartItemCount", cartService.getCartItemCount(currentUser));
        model.addAttribute("user", currentUser);

        List<CheckoutInfo> savedCheckoutInfos = checkoutService.getUserCheckoutInfos(currentUser);
        model.addAttribute("savedCheckoutInfos", savedCheckoutInfos);

        checkoutService.getDefaultCheckoutInfo(currentUser).ifPresent(defaultInfo -> {
            model.addAttribute("defaultCheckoutInfo", defaultInfo);
        });

        return "checkout";
    }

    @PostMapping("/checkout/save")
    @ResponseBody
    public String saveCheckoutInfo(@ModelAttribute CheckoutInfo checkoutInfo, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "error";
        }

        checkoutInfo.setUser(currentUser);
        checkoutService.saveCheckoutInfo(checkoutInfo);
        return "success";
    }

    @GetMapping("/checkout/info/{id}")
    @ResponseBody
    public CheckoutInfo getCheckoutInfo(@PathVariable Long id, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return null;
        }

        return checkoutService.getCheckoutInfoById(id, currentUser).orElse(null);
    }

    @PostMapping("/checkout/process")
    @ResponseBody
    public String processCheckout(@RequestParam(required = false) Long checkoutInfoId, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "error";
        }

        // Check if cart is empty
        List<com.japonbaligi.letgoclone.entity.CartItem> cartItems = cartService.getCartItems(currentUser);
        if (cartItems.isEmpty()) {
            return "empty_cart";
        }

        if (checkoutInfoId != null) {
            if (!checkoutService.getCheckoutInfoById(checkoutInfoId, currentUser).isPresent()) {
                return "invalid_info";
            }
        }

        String receiptId = checkoutService.generateReceiptId();

        cartService.clearCart(currentUser);

        return receiptId;
    }
}
