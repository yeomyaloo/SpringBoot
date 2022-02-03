package com.yeomyaloo.book.springboot.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/posts/save") //2. url 주소가 이렇게 되는 곳으로 연결해줌.
    public String postsSave(){
        return "posts-save";//1. 이 이름이 있는 index파일을 찾아서
    }
}
