package com.kht.ecommerce.ecommerce_application.mapper;

import com.kht.ecommerce.ecommerce_application.dto.KHTBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<KHTBook> getAllBooks();
    KHTBook getBookById(int id);
}
