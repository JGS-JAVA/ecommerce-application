package com.kht.ecommerce.ecommerce_application.service;

import com.kht.ecommerce.ecommerce_application.dto.KHTBook;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public interface BookService {
    List<KHTBook> getAllBooks();

    KHTBook getBookById(int id);
}
