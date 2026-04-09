package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.model.Product;
import com.klu.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService service;

    // Insert Product
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product p) {
        return service.addProduct(p);
    }

    // Category Search
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }

    // Price Filter
    @GetMapping("/filter")
    public List<Product> filterPrice(@RequestParam double min,
                                     @RequestParam double max) {
        return service.filterByPrice(min, max);
    }

    // Sort by Price
    @GetMapping("/sorted")
    public List<Product> sortProducts() {
        return service.sortByPrice();
    }

    // Expensive Products
    @GetMapping("/expensive/{price}")
    public List<Product> expensive(@PathVariable double price) {
        return service.expensiveProducts(price);
    }

}