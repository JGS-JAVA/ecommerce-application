package com.kht.ecommerce.ecommerce_application.mapper;

import com.kht.ecommerce.ecommerce_application.dto.KHTBook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper // xml 에 쓴 sql 문을 id 명으로 가져와 사용대기중인 상태 -> 대기중이고 동작 안하니까 인터페이스다
public interface BookMapper {
    List<KHTBook> findAll(); // List<DTO파일명>
    KHTBook findById(int id);
    int updateById(int id, String title,
                   String author, String genre,
                   String imagePath);
}
