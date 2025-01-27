package com.kht.ecommerce.ecommerce_application.controller;

import com.kht.ecommerce.ecommerce_application.dto.KHTBook;
import com.kht.ecommerce.ecommerce_application.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.util.List;
// html 파일명과 api 주소와 다르게 작성
@RequestMapping("/api") // 모든 url 앞에 /books url 자동 추가
@RestController
public class ApiBookController {

    // jquery ajax 를 활용해서 html 데이터를 주고 받는 json 형식으로 전송
    @Autowired
    BookServiceImpl bookService;
    // /books로 GET 요청을 받아 책 목록 데이터를 반환
    @GetMapping("/bookList")
    public List<KHTBook> findAll() {
        return bookService.findAll();
    }


// /books/{id}로 GET 요청을 받아 특정 책의 상세 정보를 반환

    @GetMapping("/books/{id}")
    public KHTBook findById(@PathVariable int id) {
        return bookService.findById(id);
    }
    // /books/{id}/update  POST 요청을 받아 특정 책 정보를 수정
    @PutMapping("/books/{id}/update")
    public int update(@PathVariable int id,
                      @RequestParam("title") String title,
                      @RequestParam("author") String author,
                      @RequestParam("genre") String genre,
                      @RequestParam("imagePath") MultipartFile imagePath) {
        System.out.println("======================= Controller 출력 =======================");
        System.out.println("title : " + title);
        System.out.println("author : " + author);
        System.out.println("genre : " + genre);
        System.out.println("imagePath : " + imagePath);
        System.out.println("===============================================================");
        return  bookService.updateById(id,title, author, genre, imagePath);
        //return 책 정보 수정 서비스 연결;
    }
    /*
    public int update(@PathVariable int id, @RequestBody KHTBook book) {
        return  bookService.updateById(book);
        //return 책 정보 수정 서비스 연결;
    }

     */


    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable int id) {
        return bookService.deleteById(id);
    }

}
