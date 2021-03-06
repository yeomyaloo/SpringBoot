package com.yeomyaloo.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//Posts 클래스로 데이터베이스를 접근하게 해줄 JpaRepository를 생성
public interface PostsRepository extends JpaRepository<Posts,Long> {

    /* 단순 인터페이스르 생성 후 JpaRepository를 상속 받아주면 기본적인 CURD 메소드가 자동으로 생성된다.*/
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
