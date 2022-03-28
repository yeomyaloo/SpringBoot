package hello.springmvc.basic.request;


import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    //기본 사용
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age={}",username,age);

        response.getWriter().write("ok");
    }

    // view 이름을 찾아 돌려주는 @Controller를 사용했기에 아래와 같은 애노테이션 사용
    //@ResponseBody를 사용해서 뷰를 넘기지 않고 String을 Body로 넘기는 방법
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username = {}, age = {}", memberName, memberAge);
        return "ok";
    }

    //V2에서 파라미터 이름과 변수 이름을 동일하게 맞춰 더욱 간략해진 코드
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    //파라미터 이름과 변수 이름이 같으면 파라미터 이름 생략 가능한 경우
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    //파라미터에 값이 있어야 하는지 없어야 하는지에 관한 required
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        //자바 내에서 int와 같은 기본타입엔 null값이 없기에 500 에러를 막기 위해 Integer을 사용해야 한다.
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    //defaultValue 사용 -> required 필요 X 값이 있든 없든 값이 들어가기 때문이다.
    //또한 빈문자에도 설정한 값이 들어간다. (빈문자로 처리해줌)
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    //파라미터를 Map으로 조회하기 - requestParamMap
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
// @ModelAttribute 사용
    /*@ModelAttribute 사용 없이 본래 코드
      요청 파라미터를 받아 필요한 객체를 생성해서 객체에 그 값을 넣어주는 작업
    * */
//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
//
//        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
//        return "ok";
//    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
    //@ModelAttribute생략 ver
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    
}
