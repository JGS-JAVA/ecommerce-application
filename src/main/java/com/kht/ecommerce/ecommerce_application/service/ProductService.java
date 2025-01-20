package com.kht.ecommerce.ecommerce_application.service;

import com.kht.ecommerce.ecommerce_application.dto.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    void addProduct();
}
