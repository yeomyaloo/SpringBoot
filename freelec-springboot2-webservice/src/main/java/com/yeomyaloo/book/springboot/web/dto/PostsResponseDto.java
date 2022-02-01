package com.yeomyaloo.book.springboot.web.dto;

import lombok.Getter;
import posts.Posts;

@Getter
public class PostsResponseDto {
    private Long id;
    private String content;
    private String title;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title= entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
