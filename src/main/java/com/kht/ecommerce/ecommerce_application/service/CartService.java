package com.kht.ecommerce.ecommerce_application.service;

import com.kht.ecommerce.ecommerce_application.dto.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    List<Cart> getCartByUserId(int userId);
}
