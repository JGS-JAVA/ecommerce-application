package com.kht.ecommerce.ecommerce_application.mapper;

import com.kht.ecommerce.ecommerce_application.dto.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    // 사용자 조회
    List<User>  getAllUsers();
    // 사용자 저장 -> 다수저장시 void 를 int 로 바꿔 몇개 저장하는지 적는다
    void insertUser(User user);

    // 이메일 존재 유무 확인
    int existByEmail(String email);
    User getUserById(int id);
    
    // put과 post 는 int get = 몇개 찾았는지 적는다
    int editUser(User user);
}
