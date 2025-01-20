package com.kht.ecommerce.ecommerce_application.service;

import com.kht.ecommerce.ecommerce_application.dto.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getCartByUserId(int userId);
}
