package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; //MVC 모델 - 뷰 - 컨트롤러 패턴에 M이 모델임
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello"; //

    }
}
