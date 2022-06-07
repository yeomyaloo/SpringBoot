package com.example.demo.controller;


import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//JSON 형태로 객체를 돌려주기 위한 용도로 쓰이는 애너테이션이다.
@RestController

//http://localhost:8080/host로 경로를 매핑 시켜준다
@RequestMapping("test") //리소스
public class TestController {
    @GetMapping
    public String testController(){
        return "Hello world!";
    }

    @GetMapping("/testGetMapping")
    public String testControllerWithPath(){
        return "Hello world! testGetMapping";
    }

    @GetMapping("/{id}")
    //@PathVariable(required = false) 매개변수가 꼭 필요한 것이 아니라는 의미로 id를 명시하지 않아도 에러가 나지 않는다.
    public String testControllerWithPathVariables(@PathVariable(required = false) int id){
        return "Hello world! ID " + id;
    }

    // @PathVariable과 동일하지만 ?id={id}와 같이 요청 매개변수로 넘어오는 값을 변수로 받을 수 있다.
    //http://localhost:8080/test/testRequestParam?id=123
    @GetMapping("/testRequestParam")
    public String testControllerWithRequestParam(@RequestParam(required = false) int id){
        return "Hello world! ID " + id;
    }

    //http://localhost:8080/test/testRequestBody
    //Body를 JSON으로 바꿔서 넣어주어야 제대로 확인이 된다.
    @GetMapping("/testRequestBody")
    //JSON 형태로 HTTPRESPONSE에 담아 반환해주는 애너테이션이다.
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
        return "Hello world! ID " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
    }


    //ResponseDTO를(= 즉, 객체를 반환한다는 뜻이다.) 반환하는 컨트롤러
    //http://localhost:8080/test/testResponseBody
    @GetMapping("/testResponseBody")
    public ResponseDTO<String> testControllerResponseBody(){
        List<String> list = new ArrayList<>();
        list.add("Hello world! I'm ResponseDTO");

        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return response;
    }

    //엔티티를 반환하는 컨트롤러 메서드(이것도 뭐 객체를 반환하는 것임)
    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity(){
        List<String> list = new ArrayList<>();
        list.add("Hello world! I'm ResponseEntity. And you got 404");

        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();

        //http 상태 코드를 404로 설정해준다.
        return ResponseEntity.badRequest().body(response);
    }


}
