package com.yeomyaloo.book.springboot.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import posts.Posts;

// Controller와 Service의 중간에서 사용할 Dto 클래스
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .author(author)
                .content(content)
                .build();
    }
}
