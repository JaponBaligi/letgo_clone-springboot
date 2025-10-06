package com.japonbaligi.letgoclone.service;

import com.japonbaligi.letgoclone.entity.Product;
import com.japonbaligi.letgoclone.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getFeaturedProducts() {
        return productRepository.findAll().stream()
                .limit(8)
                .toList();
    }

    public List<Product> searchProducts(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllProducts();
        }
        return productRepository.searchProducts(query.trim());
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }

    public List<String> getAllLocations() {
        return productRepository.findAllLocations();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void initializeSampleData() {
        if (productRepository.count() == 0) {
            Product product1 = new Product(
                    "iPhone 13 Pro Max 256GB",
                    "Mükemmel durumda, kutusu ve aksesuarları ile birlikte satılık iPhone 13 Pro Max.",
                    new BigDecimal("25000"),
                    "https://productimages.hepsiburada.net/s/337/424-600/110000157420823.jpg/format:webp",
                    "Telefon & Tablet",
                    "İstanbul, Kadıköy",
                    "İkinci El");

            Product product2 = new Product(
                    "MacBook Air M2 512GB",
                    "2023 model MacBook Air M2 çip, 512GB SSD, 8GB RAM. Çok az kullanılmış.",
                    new BigDecimal("35000"),
                    "https://m.media-amazon.com/images/I/71f5Eu5lJSL._AC_SL1500_.jpg",
                    "Bilgisayar",
                    "Ankara, Çankaya",
                    "İkinci El");

            Product product3 = new Product(
                    "BMW X5 2020 Model",
                    "2020 model BMW X5, 3.0L dizel motor, otomatik vites, full donanım.",
                    new BigDecimal("850000"),
                    "https://hips.hearstapps.com/hmg-prod/images/2020-bmw-x5-m-113-1582911123.jpg?crop=0.758xw:0.825xh;0.192xw,0.175xh&resize=1200:*",
                    "Otomobil",
                    "İzmir, Konak",
                    "İkinci El");

            Product product4 = new Product(
                    "PlayStation 5 + 2 Oyun",
                    "PlayStation 5 konsol + FIFA 24 ve Spider-Man 2 oyunları ile birlikte.",
                    new BigDecimal("18000"),
                    "https://cdn03.ciceksepeti.com/cicek/kcm34663295-1/L/sony-playstation-5-slim-standart-edition-oyun-konsolu-2-dualsense-kol-ithalatci-garantili-kcm34663295-1-01fd4e6328f6443ab8930c10e0533cd7.jpg",
                    "Oyun & Hobi",
                    "Bursa, Nilüfer",
                    "İkinci El");

            Product product5 = new Product(
                    "Samsung Galaxy S23 Ultra",
                    "Samsung Galaxy S23 Ultra 256GB, S Pen dahil, kutusu ile birlikte.",
                    new BigDecimal("22000"),
                    "https://m.media-amazon.com/images/I/51N+oXYtlcL._AC_SL1200_.jpg",
                    "Telefon & Tablet",
                    "İstanbul, Beşiktaş",
                    "İkinci El");

            Product product6 = new Product(
                    "AirPods Pro 2. Nesil",
                    "Apple AirPods Pro 2. nesil, aktif gürültü engelleme, kutusu ile.",
                    new BigDecimal("4500"),
                    "https://m.media-amazon.com/images/I/51HCs2mVKsL._AC_SL1500_.jpg",
                    "Telefon & Tablet",
                    "Ankara, Keçiören",
                    "İkinci El");

            Product product7 = new Product(
                    "Nike Air Max 270",
                    "Nike Air Max 270 erkek ayakkabı, 42 numara, çok az giyilmiş.",
                    new BigDecimal("1200"),
                    "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/pyyixbczj6u5kiwhpjik/W+AIR+MAX+270.png",
                    "Moda & Giyim",
                    "İstanbul, Şişli",
                    "İkinci El");

            Product product8 = new Product(
                    "IKEA Yatak Odası Takımı",
                    "IKEA yatak odası takımı, gardırop, komodin ve yatak dahil.",
                    new BigDecimal("8500"),
                    "https://img.vivense.com/1920x1280/images/31c66bdd5e764e078458a6a543c02efe.jpg",
                    "Ev & Bahçe",
                    "İzmir, Bornova",
                    "İkinci El");

            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
            productRepository.save(product5);
            productRepository.save(product6);
            productRepository.save(product7);
            productRepository.save(product8);
        }
    }
}
