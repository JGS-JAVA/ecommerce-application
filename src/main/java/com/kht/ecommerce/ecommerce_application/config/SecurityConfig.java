package com.kht.ecommerce.ecommerce_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //설정파일
public class SecurityConfig {

    // SecurityConfig 이외 다른파일에서 PasswordEncoder 사용 가능하게 @Bean 작성
    // PasswordEncoder.java 파일가져와 비밀번호 암호화기능 사용하겠다 설정
    @Bean // 비밀번호 암호화 처리 자바기능
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin(form -> form.disable());
        return http.build();  // 위에서 작성한 http 속성으로 만들기
    }
    /*
     * CSRF = Cross Site Request Forgery
     * 공격자가 사용자의 권한을 도용해서 악의적인 요청을 서버로 보내게 유도하는 방식
     * csrf.disable = 개발단계에서 비활성화해 코드작성에 집중
     *
     * authorizeHttpRequests = http 요청에 권한 설정 메서드
     * anyRequest = 모든 요청을
     * permitAll = 모두 허용
     * 개발단계에서 코딩에 방해 안되게 설정
     *
     * formLogin (form -> form.disable()) = build.gradel 파일의 dependencies 에
     *    implementation 'org.springframework.boot:spring-boot-starter-security' 작성해 사용 활성화시
     * 자동으로 보안설정되고 관리자 권한 가진 아이디로 로그인해야 사이트 이용가능하게 초기설정 돼있는걸 비활성화
     *
     *
     * */
}
