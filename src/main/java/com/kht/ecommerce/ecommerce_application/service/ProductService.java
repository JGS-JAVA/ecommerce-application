package com.kht.ecommerce.ecommerce_application.service;

import com.kht.ecommerce.ecommerce_application.dto.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    List<Product> getAllProducts();

    void addProduct();
    Product findById(int id);
    void updateProduct(Product product);
}
