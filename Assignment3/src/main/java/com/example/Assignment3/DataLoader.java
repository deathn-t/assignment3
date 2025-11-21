package com.example.Assignment3;

import com.example.Assignment3.Cart.CartRepository;
import com.example.Assignment3.Products.Product;
import com.example.Assignment3.Products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSampleProducts();
        System.out.println("Application started successfully!");
        System.out.println("Products in database: " + productRepository.count());
        System.out.println("Cart items: " + cartRepository.count());
    }

    private void loadSampleProducts() {
        // Only load if no products exist
        if (productRepository.count() == 0) {
            List<Product> sampleProducts = Arrays.asList(
                new Product("Nike Air Max", "Comfortable running shoes with air cushioning", new BigDecimal("129.99"), "NIKE-AIR-001", "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=400"),
                new Product("Adidas Ultraboost", "High-performance running shoes", new BigDecimal("179.99"), "ADID-UB-001", "https://images.unsplash.com/photo-1606107557195-0e29a4b5b4aa?w=400"),
                new Product("Puma RS-X", "Retro-inspired sneakers with bold design", new BigDecimal("89.99"), "PUMA-RSX-001", "https://images.unsplash.com/photo-1605348532760-6753d2c43329?w=400"),
                new Product("Converse Chuck Taylor", "Classic canvas sneakers", new BigDecimal("59.99"), "CONV-CT-001", "https://images.unsplash.com/photo-1525966222134-fcfa99b8ae77?w=400"),
                new Product("Vans Old Skool", "Iconic skate shoes with side stripe", new BigDecimal("69.99"), "VANS-OS-001", "https://images.unsplash.com/photo-1549298916-b41d501d3772?w=400"),
                new Product("New Balance 574", "Classic lifestyle running shoes", new BigDecimal("84.99"), "NB-574-001", "https://images.unsplash.com/photo-1600185365483-26d7a4cc7519?w=400"),
                new Product("Reebok Classic", "Vintage-style leather sneakers", new BigDecimal("79.99"), "REE-CL-001", "https://images.unsplash.com/photo-1600185365926-3a2ce3cdb9eb?w=400"),
                new Product("Nike Jordan 1", "Basketball-inspired high-top sneakers", new BigDecimal("149.99"), "NIKE-J1-001", "https://images.unsplash.com/photo-1556906781-2a6f8f753cad?w=400"),
                new Product("Adidas Stan Smith", "Classic tennis shoes with green heel tab", new BigDecimal("89.99"), "ADID-SS-001", "https://images.unsplash.com/photo-1543508282-6319a3e2621f?w=400"),
                new Product("Puma Suede", "Classic suede sneakers", new BigDecimal("74.99"), "PUMA-SD-001", "https://images.unsplash.com/photo-1460353581641-37baddab0fa2?w=400"),
                new Product("Nike Dunk Low", "Skateboarding-inspired sneakers", new BigDecimal("119.99"), "NIKE-DL-001", "https://images.unsplash.com/photo-1600269452121-4f2416e55c28?w=400"),
                new Product("Adidas NMD", "Modern running shoes with Boost technology", new BigDecimal("139.99"), "ADID-NMD-001", "https://images.unsplash.com/photo-1575537302964-96f9479d4a4e?w=400"),
                new Product("Asics Gel-Kayano", "Stability running shoes", new BigDecimal("159.99"), "ASICS-GK-001", "https://images.unsplash.com/photo-1560769624-6b69d3a2d52b?w=400"),
                new Product("Under Armour Curry", "Basketball shoes with responsive cushioning", new BigDecimal("134.99"), "UA-CUR-001", "https://images.unsplash.com/photo-1606107557195-0e29a4b5b4aa?w=400"),
                new Product("Salomon Speedcross", "Trail running shoes with aggressive grip", new BigDecimal("129.99"), "SAL-SC-001", "https://images.unsplash.com/photo-1460353581641-37baddab0fa2?w=400"),
                new Product("Brooks Ghost", "Neutral running shoes with DNA LOFT", new BigDecimal("129.99"), "BRO-GH-001", "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=400"),
                new Product("Hoka Clifton", "Maximalist running shoes with cushioning", new BigDecimal("149.99"), "HOKA-CL-001", "https://images.unsplash.com/photo-1600185365483-26d7a4cc7519?w=400"),
                new Product("Saucony Kinvara", "Lightweight performance running shoes", new BigDecimal("119.99"), "SAU-KV-001", "https://images.unsplash.com/photo-1600269452121-4f2416e55c28?w=400"),
                new Product("On Cloudflow", "Swiss-engineered running shoes", new BigDecimal("169.99"), "ON-CF-001", "https://images.unsplash.com/photo-1575537302964-96f9479d4a4e?w=400"),
                new Product("Mizuno Wave Rider", "Responsive running shoes with wave plate", new BigDecimal("139.99"), "MIZ-WR-001", "https://images.unsplash.com/photo-1543508282-6319a3e2621f?w=400")
            );
            productRepository.saveAll(sampleProducts);
            System.out.println("Loaded " + sampleProducts.size() + " sample products");
        }
    }
}