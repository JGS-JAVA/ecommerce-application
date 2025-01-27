package com.kht.ecommerce.ecommerce_application.controller;

import com.kht.ecommerce.ecommerce_application.dto.Cart;
import com.kht.ecommerce.ecommerce_application.dto.KHTBook;
import com.kht.ecommerce.ecommerce_application.dto.Product;
import com.kht.ecommerce.ecommerce_application.dto.User;
import com.kht.ecommerce.ecommerce_application.service.BookServiceImpl;
import com.kht.ecommerce.ecommerce_application.service.CartServiceImpl;
import com.kht.ecommerce.ecommerce_application.service.ProductServiceImpl;
import com.kht.ecommerce.ecommerce_application.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class ApiController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CartServiceImpl cartService;


    // 사용자 목록 API
    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/api/books")
    public List<KHTBook> getBooks() {
        return bookService.findAll();
    }

    // 사용자 상세보기 API
    @GetMapping("/api/user/{id}")
    public User getUser(@PathVariable int id) {
        User user =   userService.getUserById(id);
        log.info(user.toString());
        return user;
    }
//    @GetMapping("/api/book/{id}")
//    public User getBook(@PathVariable int id) {
//        User user =   bookService.getBookById(id);
//        log.info(user.toString());
//        return user;
//    }

    // 사용자 수정하기 api
    //  서울시 강남구 테헤란로
    @PutMapping("/api/user/edit/{id}") // PathVariable = 특정사용자 데이터를 주고받는 장소  @Request = 데이터를 통째로 전달받거나, 일부분만 전달받아서 사용하거나 전달
    public int getUserEdit(@PathVariable("id") int id,
                           @RequestBody User user) {
        System.out.println(" 유저 : " + user.toString());
        user.setId(id); // 수정하기 주소에 존재하는 유저 아이디를 가져와서 User DTO에 넣어주고, mapper.xml where id 에서 넣어준 id를 사용할 수 있음
        System.out.println(" 유저 : " + user.toString());
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
