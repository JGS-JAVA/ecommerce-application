package com.kht.ecommerce.ecommerce_application.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Controller
@RequestMapping("/oauth/kakao")
public class KakaoAPIController {

    // ${변수이름} 은 application.properties 또는 config.properties 에 작성한 변수이름에 해당하는 값 가져오기
    @Value("${kakao.client-id}") 
    private String kakaoClientId;

    /*config.properties 에서 kakao.redirect-uri 직접 가져올수 있음
    kakao.redirect-uri=http://localhost:8080/oauth/kakao/callback 
    하지만 보안상 아이디, 비번 등 중요정보는 properties 로 나눠서 @value 로 호출해서 사용한다
    */
     
    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.client-secret}")
    private String kakaoClientSecret;

    @GetMapping("/login")
    public ResponseEntity<?> getKakaoLoginUrl() {
        String url = "https://kauth.kakao.com/oauth/authorize?response_type=code" +
                "&client_id=" + kakaoClientId +
                "&redirect_uri=" + redirectUri;
        return ResponseEntity.ok(url); // ResponseEntity 는 상태확인하기. 안써도 된다
    }


    @GetMapping("/callback")
    public String handleCallback(@RequestParam String code) {
        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        if (kakaoClientSecret != null) {
            params.add("client_secret", kakaoClientSecret);
        }

        HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);
        String accessToken = (String) response.getBody().get("access_token");

        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";
        HttpHeaders userHeaders = new HttpHeaders();
        userHeaders.add("Authorization", "Bearer " + accessToken);

        HttpEntity<String> userRequest = new HttpEntity<>(userHeaders);
        // ResponseEntity 는 상태확인하기. 안써도 된다
        ResponseEntity<Map> userResponse = restTemplate.postForEntity(userInfoUrl, userRequest, Map.class);

        Map userInfo = userResponse.getBody();
        Map<String, Object> properties = (Map<String, Object>) userInfo.get("properties");
        
        // 추후 프로젝트에 맞게 카카오에서 가져올 값 설정
        String nickname = (String) properties.get("nickname"); // 닉네임 가져오기

        // 한글깨짐 방지
        String encodedNickname = URLEncoder.encode(nickname, StandardCharsets.UTF_8);


        return "redirect:/signup?nickname=" + encodedNickname;
    }
}
