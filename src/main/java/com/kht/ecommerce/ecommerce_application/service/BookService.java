package com.kht.ecommerce.ecommerce_application.service;

import com.kht.ecommerce.ecommerce_application.dto.KHTBook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.util.List;

@Service
public interface BookService { // interface 는 @autowired 안쓴다

    List<KHTBook> findAll();

    KHTBook findById(int id);

    int updateById(int id, String title, String author, String genre, MultipartFile file);

    int deleteById(int id);
}
