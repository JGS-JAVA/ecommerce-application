package com.kht.ecommerce.ecommerce_application.controller;

import com.kht.ecommerce.ecommerce_application.dto.Product;
import com.kht.ecommerce.ecommerce_application.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 뷰 는 html 파일 바라보기
// rest 는 백엔드가 디비에서 가져온 데이터를 주소값으로 전달만 하는 상태
@RestController
@RequestMapping("/api")
public class UDController {
    @Autowired
    ProductServiceImpl productService;

    // 제품 업데이트
    // html 파일에서 받은 업데이트 내용대로 디비를 수정하기
    @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setId(id);
         productService.updateProduct(product);
    }
}
