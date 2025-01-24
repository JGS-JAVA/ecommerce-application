package com.kht.ecommerce.ecommerce_application.controller;

import com.kht.ecommerce.ecommerce_application.dto.Cart;
import com.kht.ecommerce.ecommerce_application.dto.Product;
import com.kht.ecommerce.ecommerce_application.dto.User;
import com.kht.ecommerce.ecommerce_application.service.CartServiceImpl;
import com.kht.ecommerce.ecommerce_application.service.ProductServiceImpl;
import com.kht.ecommerce.ecommerce_application.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class ApiController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CartServiceImpl cartService;


    // 사용자 목록 API
    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    // 사용자 상세보기 api
    @GetMapping("/api/user/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }
    // 사용자 수정하기 api
    // PathVariable = 특정사용자 데이터 주고받는 장소(주소)
    // RequestParam = 특정 파라미터를 검색
    // 바디는 전체, 파람은 일부
    // Request = 데이터 전체/일부 전달받아 사용하거나 전달하기
    @PutMapping("/api/user/edit/{id}")
    public int getUserEdit(@PathVariable("id") int id, @RequestBody User user) {
        System.out.println(user.toString());
        user.setId(id);
        System.out.println(user.toString());
        return userService.editUser(user);
    }


    // 상품 목록 API
    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    // 특정 사용자의 장바구니 API
    // http://localhost:8080/api/carts?userId=1
    @GetMapping("/api/carts")
    public List<Cart> getCart(@RequestParam("userId") int userId) {

        return cartService.getCartByUserId(userId);
    }
    /*
        HTTP Status 500 – Internal Server Error 서버에서 생각지못한 문제 발생
        Expected one result (or null) to be returned by selectOne(), but found: 3
    */

    /*
    Param = 파라미터 = 매개변수
     * @RequestParam  부분적으로 저장할 때 사용
     * @RequestBody     전체적으로 저장할 때 사용
     * */
    @PostMapping("/api/join")
    public void join(@RequestBody User user) {
        log.info("join user: {}", user);
        userService.insertUser(user);
    }

    @GetMapping("/api/existEmail")
    public boolean existEmail(@RequestParam("email") String email) {
        /*
        앞으로 아래와 같은 기능은 서비스 Impl에서 작성할 것!!!!!
         boolean exists = userService.existByEmail(email);
        Map<String, Object> map = new HashMap<>();
        map.put("exists", exists);
        if (exists) {
            map.put("msg", "이미 사용중인 이메일입니다.");
        } else {
            map.put("msg", "사용 가능한 이메일입니다.");
        }
        return map;

        */
        return userService.existByEmail(email); // 결과를 html true false로 전달
    }

    @PostMapping("/api/products/insert")
    public void addProduct(@RequestBody Product product) {
        log.info("add product: {}", product);
        productService.addProduct();
    }

    @GetMapping("/api/product/{id}")
    public Product getProduct(@PathVariable("id") int id) {
        return productService.findById(id);
        // DB에서 가져온 데이터를 front-end 전달
    }
}
