package com.yeomyaloo.book.springboot.web;


import com.yeomyaloo.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save") //2. url 주소가 이렇게 되는 곳으로 연결해줌.
    public String postsSave(){
        return "posts-save";//1. 이 이름이 있는 index파일을 찾아서
    }


}
