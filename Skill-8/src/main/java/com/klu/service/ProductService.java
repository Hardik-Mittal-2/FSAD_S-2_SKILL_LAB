package com.klu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Product;
import com.klu.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository repo;

    public Product addProduct(Product p) {
        return repo.save(p);
    }

    public List<Product> getByCategory(String category) {
        return repo.findByCategory(category);
    }

    public List<Product> filterByPrice(double min, double max) {
        return repo.findByPriceBetween(min, max);
    }

    public List<Product> sortByPrice() {
        return repo.getProductsSortedByPrice();
    }

    public List<Product> expensiveProducts(double price) {
        return repo.getExpensiveProducts(price);
    }
}