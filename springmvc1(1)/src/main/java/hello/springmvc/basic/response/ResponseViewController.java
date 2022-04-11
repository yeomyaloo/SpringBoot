package hello.springmvc.basic.response;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    //뷰 템플릿을 호출하는 컨트롤러. 이때 data를 hello!로 치환해준다. (정적 리소스에서 보면 타임리프 문버임)
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }


    //물리적 경로를 찾아서 그 찾은 뷰를 렌더링 해준다.
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "hello!");
        return "response/hello";
    }

    //권장하지 않는 방법(불명확하기 때문이다.)
    //컨트롤러 이름과 뷰의 논리적 이름이 똑같으면 관례적으로 생략 가능하게 해줌 권장XXX
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data", "hello!");
    }
}
