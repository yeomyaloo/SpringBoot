package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; //MVC 모델 - 뷰 - 컨트롤러 패턴에 M이 모델임
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello"; //

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }
    @GetMapping("hello-string")
    @ResponseBody //바디부에 직접 내가 return 값을 넣어주겠다는 의미!!
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //"hello spring" --> 네임에 넣는 것에 따라 달라짐. view 없이 그대로 문자가 내려감!!
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체를 넣은 경우
    }
    static class Hello{ //static class로 넣어서 사용가능 자바에서 정식지원해주는 기능(내부클래스)
        private String name;

        // Alt + insert(ins) 버튼을 누르면 자동으로 만들어줌
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
