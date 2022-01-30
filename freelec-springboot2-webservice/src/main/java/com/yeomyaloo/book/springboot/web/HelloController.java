package com.yeomyaloo.book.springboot.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//컨트롤러를 JSON 반환하는 컨트롤러로 만들어 준다.
public class HelloController {

    //HTTP Method인 get의 요청을 받을 수 있는 API를 만들어준다.
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}
