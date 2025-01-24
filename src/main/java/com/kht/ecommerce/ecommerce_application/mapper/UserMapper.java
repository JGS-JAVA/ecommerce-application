package com.kht.ecommerce.ecommerce_application.mapper;

import com.kht.ecommerce.ecommerce_application.dto.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    // 디비에서 데이터 가져올 때 DTO 사용
    // 사용자 조회
    List<User>  getAllUsers();
    // 사용자 저장 -> 다수 저장시 갯수 중요하면 void 를 int 로 바꿔 몇개 저장하는지 적는다
    void insertUser(User user);

    // 이메일 존재 유무 확인
    int existByEmail(String email);
    User getUserById(int id);
    
    // put과 post 는 int get = 몇개 찾았는지 적는다
    // 1개만 수정시 int 를 void 로 작성가능. 다수 수정시 int 작성
    int editUser(User user);
}
