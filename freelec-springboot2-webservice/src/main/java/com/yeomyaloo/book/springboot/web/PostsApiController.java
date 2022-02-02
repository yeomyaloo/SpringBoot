package com.yeomyaloo.book.springboot.web;


import com.yeomyaloo.book.springboot.service.posts.PostsService;
import com.yeomyaloo.book.springboot.web.dto.PostUpdateRequestDto;
import com.yeomyaloo.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    //final 키워드가 붙어서 꼭 값을 돌려주어야 한다!
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }
    @GetMapping("/api/v1/posts/{id}")
    public PostsSaveRequestDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }


}
