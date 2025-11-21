package com.example.Assignment3.Cart;

import com.example.Assignment3.Products.Product;
import com.example.Assignment3.Products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<CartItem> getCartItems() {
        List<CartItem> cartItems = cartRepository.findAll();

        // Enrich cart items with product details
        return cartItems.stream().map(cartItem -> {
            Optional<Product> product = productRepository.findById(cartItem.getProductId());
            if (product.isPresent()) {
                Product p = product.get();
                cartItem.setProductName(p.getName());
                cartItem.setProductPrice(p.getPrice());
                cartItem.setProductImage(p.getImage());
            }
            return cartItem;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItem cartItem) {
        // Check if product exists
        Optional<Product> product = productRepository.findById(cartItem.getProductId());
        if (product.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Check if item already in cart
        Optional<CartItem> existingItem = cartRepository.findByProductId(cartItem.getProductId());
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + cartItem.getQuantity());
            return ResponseEntity.ok(cartRepository.save(item));
        }

        return ResponseEntity.ok(cartRepository.save(cartItem));
    }

    @DeleteMapping("/emptycart")
    public ResponseEntity<?> emptyCart() {
        cartRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long cartId) {
        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getCartTotal() {
        List<CartItem> cartItems = getCartItems();
        double total = cartItems.stream()
                .mapToDouble(item -> item.getProductPrice().doubleValue() * item.getQuantity())
                .sum();
        return ResponseEntity.ok(total);
    }
}