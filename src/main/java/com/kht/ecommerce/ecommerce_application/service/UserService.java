package com.kht.ecommerce.ecommerce_application.service;

import com.kht.ecommerce.ecommerce_application.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface UserService {
    List<User> getAllUsers();

    // 사용자 저장
    void insertUser(User user);

    // 이메일 존재 유무 확인
   // Map<String, Object> existByEmail(String email);
    boolean existByEmail(String email);
    User getUserById(int id);
}