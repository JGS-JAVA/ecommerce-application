package com.kht.ecommerce.ecommerce_application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 카카오톡 로그인 버튼 회원가입 html 이 보이는 view controller
@Controller
public class KakaoViewController {

    // 1. kakaoLogin.html 연결
    @GetMapping("/kakaoLogin")
    public String kakaoLogin() {
        return "login";
    }
    
    /*
    * 500 parameter - lang 오류
    * redirect 전달받을때 ? 킵=값 전달
    * 전달할때 요청받은 requestParam 값 없으면 500 에러 발생
    * */
    
    
    @GetMapping("/signup")
    public String kakaoSignUp(@RequestParam("nickname")
                                  String nickname,    Model model) {
        model.addAttribute("nickname", nickname);
        return "signup";
    }
}
