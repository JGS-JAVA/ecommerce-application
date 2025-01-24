package com.kht.ecommerce.ecommerce_application.service;

import com.kht.ecommerce.ecommerce_application.dto.User;
import com.kht.ecommerce.ecommerce_application.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

//SecurityConfig 내부에 빈 태그 설정한 BCrypto 호출
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Autowired // @Bean 또는 @Bean 내장된 것들 호출
    private PasswordEncoder passwordEncoder; // SecurityConfig.java 에 작성된 기능 가져오기

    @Override
    public void insertUser(User user) {
        System.out.println(user.getPassword()); // html - controller - service 로 비밀번호 가져오기

        String encodePassword = passwordEncoder.encode(user.getPassword()); // 비번 암호화 처리 확인 변수명
        System.out.println(passwordEncoder.encode(user.getPassword()));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user); // DB에서 가져온 값을 return해서 반환할 필요 X
        // DB에 저장을 하는 것이지 데이터베이스에서 꺼내오는 것이 아니기 때문에
        // return 을 사용하지 않고 void 사용
    }

    @Override
    public boolean existByEmail(String email) {
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
        return userMapper.existByEmail(email) > 0 ;
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }
    @Override
    public int editUser(User user) {
        return userMapper.editUser(user);
    }
}
