package com.japonbaligi.letgoclone.controller;

import com.japonbaligi.letgoclone.entity.Product;
import com.japonbaligi.letgoclone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Takı Kazan");

        productService.initializeSampleData();

        List<Product> featuredProducts = productService.getFeaturedProducts();
        model.addAttribute("featuredProducts", featuredProducts);

        List<String> categories = productService.getAllCategories();
        model.addAttribute("categories", categories);

        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "q", required = false) String query, Model model) {
        model.addAttribute("searchQuery", query);
        model.addAttribute("title", "Arama Sonuçları - Takı Kazan");

        List<Product> searchResults;
        if (query != null && !query.trim().isEmpty()) {
            searchResults = productService.searchProducts(query);
        } else {
            searchResults = productService.getAllProducts();
        }

        model.addAttribute("searchResults", searchResults);
        model.addAttribute("resultCount", searchResults.size());

        List<String> categories = productService.getAllCategories();
        model.addAttribute("categories", categories);

        List<String> locations = productService.getAllLocations();
        model.addAttribute("locations", locations);

        return "search";
    }
}
