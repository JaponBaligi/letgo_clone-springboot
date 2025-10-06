package com.japonbaligi.letgoclone.controller;

import com.japonbaligi.letgoclone.entity.User;
import com.japonbaligi.letgoclone.entity.Product;
import com.japonbaligi.letgoclone.service.AuthService;
import com.japonbaligi.letgoclone.service.CartService;
import com.japonbaligi.letgoclone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("title", "Giriş Yap - Takı Kazan");
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam String username,
            @RequestParam String password,
            HttpSession session) {

        if (authService.authenticate(username, password)) {
            User user = authService.getUserByUsername(username);
            session.setAttribute("user", user);
            session.setAttribute("isLoggedIn", true);
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping("/logout")
    @ResponseBody
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("isLoggedIn");
        return "success";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("title", "Kayıt Ol - Takı Kazan");
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String fullName,
            HttpSession session) {

        if (authService.userExists(username)) {
            return "username_exists";
        }

        if (authService.emailExists(email)) {
            return "email_exists";
        }

        User user = authService.createUser(username, password, email, fullName);
        session.setAttribute("user", user);
        session.setAttribute("isLoggedIn", true);

        return "success";
    }

    @GetMapping("/cart")
    public String cartPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        try {
            model.addAttribute("title", "Sepetim - Takı Kazan");
            model.addAttribute("cartItems", cartService.getCartItems(user));
            model.addAttribute("cartTotal", cartService.getCartTotal(user));
            model.addAttribute("cartItemCount", cartService.getCartItemCount(user));
        } catch (Exception e) {
            model.addAttribute("title", "Sepetim - Takı Kazan");
            model.addAttribute("cartItems", List.of());
            model.addAttribute("cartTotal", 0.0);
            model.addAttribute("cartItemCount", 0);
        }

        return "cart";
    }

    @PostMapping("/cart/add")
    @ResponseBody
    public String addToCart(@RequestParam Long productId,
            @RequestParam(defaultValue = "1") Integer quantity,
            HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "not_logged_in";
        }

        try {
            Product product = productService.getProductById(productId).orElse(null);
            if (product == null) {
                return "product_not_found";
            }

            cartService.addToCart(user, product, quantity);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/cart/remove")
    @ResponseBody
    public String removeFromCart(@RequestParam Long productId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "not_logged_in";
        }

        try {
            Product product = productService.getProductById(productId).orElse(null);
            if (product == null) {
                return "product_not_found";
            }

            cartService.removeFromCart(user, product);
            return "success";
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception to see what's causing the error
            return "error";
        }
    }

    @PostMapping("/cart/update")
    @ResponseBody
    public String updateCartItem(@RequestParam Long productId,
            @RequestParam Integer quantity,
            HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "not_logged_in";
        }

        try {
            Product product = productService.getProductById(productId).orElse(null);
            if (product == null) {
                return "product_not_found";
            }

            cartService.updateCartItemQuantity(user, product, quantity);
            return "success";
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception to see what's causing the error
            return "error";
        }
    }

    @PostMapping("/cart/clear")
    @ResponseBody
    public String clearCart(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "not_logged_in";
        }

        try {
            cartService.clearCart(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception to see what's causing the error
            return "error";
        }
    }

    @GetMapping("/auth/status")
    @ResponseBody
    public String getAuthStatus(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if (user != null && isLoggedIn != null && isLoggedIn) {
            return "{\"loggedIn\": true, \"username\": \"" + user.getUsername() + "\"}";
        } else {
            return "{\"loggedIn\": false}";
        }
    }
}
