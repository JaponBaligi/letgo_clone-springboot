package com.japonbaligi.letgoclone.controller;

import com.japonbaligi.letgoclone.entity.Product;
import com.japonbaligi.letgoclone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);

        if (product.isPresent()) {
            model.addAttribute("title", product.get().getTitle() + " - Takı Kazan");
            model.addAttribute("product", product.get());

            model.addAttribute("relatedProducts",
                    productService.getProductsByCategory(product.get().getCategory()));

            return "product-detail";
        } else {
            return "redirect:/";
        }
    }
}
