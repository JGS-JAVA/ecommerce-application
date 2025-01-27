package com.kht.ecommerce.ecommerce_application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
* url 주소값 작성
* ?들어가는 파라미터는 반드시 마지막에 작성
* /     /       /?=
* parameter 중간에 위치시 값을 못찾을 수 있다
* */

@Controller
public class ViewBookController {

    @GetMapping("/books") // 기본값
  //  @GetMapping(value="/books", params={}) // 기본값
    public String bookList() {
        return "books";
    }

    // 매핑 = 특정 주소값으로 기능이나 전달하는 행위를 감싸서 한번에 전달하기
    // get 전달 post 저장 put 수정 delete 삭제
    // value = 주소값, 파라미터는 주소값 뒤나 중간에 쓰거나 생략한다
    @GetMapping(value= "/books", params = "id")
    //@GetMapping("/books/detail") //1. /books/detail?id=id값
   // @GetMapping(value = "/books",params="id") // 2. params = "id"

     public String bookDetails(@RequestParam("id") int id) {

    return "book-detail";
    }
    
    // 주소값은 뒤에 ?id= 쓰는게 제일 좋다
    @GetMapping("/books/update")
    public String updateBook(@RequestParam("id") int id, Model model) {
      //  model.addAttribute("bookId", id);
        return "book-edit";
    }
}
