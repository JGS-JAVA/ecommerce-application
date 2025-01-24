package com.kht.ecommerce.ecommerce_application.service;


import com.kht.ecommerce.ecommerce_application.dto.Product;
import com.kht.ecommerce.ecommerce_application.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }

    @Override
    public void addProduct() {
        productMapper.addProduct();
    }

    @Override
    public Product findById(int id) {
        return productMapper.findById(id);
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }
}