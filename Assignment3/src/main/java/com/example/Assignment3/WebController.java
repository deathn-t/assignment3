package com.example.Assignment3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String products() {
        return "forward:/products.html";
    }

    @GetMapping("/products")
    public String productsPage() {
        return "forward:/products.html";
    }

    @GetMapping("/cart")
    public String cartPage() {
        return "forward:/cart.html";
    }
}