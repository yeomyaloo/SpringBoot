package com.yeomyaloo.book.springboot.domain.posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter //
@NoArgsConstructor //기본 생성자 자동 추가
@Entity //테이블과 링크될 클래스임을 나타냄 *절대 Entity 클래스에서는 Setter 메소드를 만들지 않는다.*
public class Posts {
    @Id //PK 생성 규칙
    @GeneratedValue
    private Long id;

    @Column(length = 500,nullable = false) //굳이 선언하지 않아도 전부 Column 선언 됨
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.author = author;
        this.content = content;
        this.title = title;
    }

}
