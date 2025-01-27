package com.kht.ecommerce.ecommerce_application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // config.properties 에 작성한 파일저장경로 설정
    @Value("c:/upload-path")
    private String uploadPath;

    // 이미지 처리시
    // 1. static 폴더 아래 이미지
    // 2. 업로드 폴더 위치 이미지 설정
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    // 이미지 위치별 거짓 경로 설정하기
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");

        registry.addResourceHandler("/uploaded/**")
                .addResourceLocations("file:" + uploadPath+"/");

    }

}
