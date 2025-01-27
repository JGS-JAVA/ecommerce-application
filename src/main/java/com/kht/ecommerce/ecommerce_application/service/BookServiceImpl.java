package com.kht.ecommerce.ecommerce_application.service;

import com.kht.ecommerce.ecommerce_application.dto.KHTBook;
import com.kht.ecommerce.ecommerce_application.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

// 실질적으로 서비스 기능을 하고 있는 파일은
// Impl 구현 목록을 받아 상세기능을 작성한 class 파일이기 때문에
// Impl 붙은 파일에 @Service 어노테이션 설정
@Service
public class BookServiceImpl implements BookService {
    // 세부 기능을 구현하고, 특정 sql문으로 기능 작동을 수행하기 위해
    // @Autowired 를 사용
    @Value("${upload-img}") // config.properties 에 작성한 경로 가져오기
    private String uploadImg;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<KHTBook> findAll() {
        return bookMapper.findAll();
    }

    @Override
    public KHTBook findById(int id) {
        return bookMapper.findById(id);
    }

    @Override
    public int updateById(int id, String title, String author, String genre, MultipartFile imagePath) {
        System.out.println("======================= Service 출력 =======================");
        System.out.println("title : " + title);
        System.out.println("author : " + author);
        System.out.println("genre : " + genre);
        System.out.println("imagePath : " + imagePath);

        try {


            String imgPath = imagePath.getOriginalFilename(); // 이미지에서 가져온 파일이름
            System.out.println("OriginalFilename : " + imgPath);
            // 이미지 저장 경로에 이미지파일을 저장하고
            File file = new File(uploadImg,imgPath);
            //  어떤파일을.저장할것이다(어디에다가+어떤이름으로);
            imagePath.transferTo(file);

            KHTBook book = new KHTBook();
            book.setId(id);
            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);
            // 가짜 이미지경로로 /images 설정
            book.setImagePath("/upload/"+imgPath); //이미지는 파일에서 이미지이름만 추출한다음 글자형태로 db 저장
            // 이미지 이름만 get 가져와서 String 위치 + 이미지 이름 만 DB 저장!
            return bookMapper.updateById(id, title, author, genre, imgPath); // = 1번
           // 2번 = return bookMapper.updateById(book); // book 변수 안에 id, title, author, genre, imgPath 다 들어있다
           // 1번 대신 2번 써도 된다

        } catch (IOException e) {
            // 개발자가 컴퓨터 작업에 문제가 있을 때 문제를 확인하는 멘트
            System.out.println("파일을 컴퓨터에 저장할 수 없고, 데이터베이스에 수정할 수 없습니다.");
            return 0;
        }
    }

    @Override
    public int deleteById(int id) {
        return bookMapper.deleteById(id);
    }
    /*
    @Override
    public int updateById(KHTBook khtBook) {
        return bookMapper.updateById(khtBook);
    }

     */
}
