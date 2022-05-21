package hello.exception.servlet;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//오류가 났을 때 오류 화면으로 이동시켜주기 위한 컨트롤러
@Slf4j
@Controller
public class ErrorPageController {

    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletResponse response, HttpServletRequest request){
        log.info("errorPage 404");
        return "error-page/404";
    }
    @RequestMapping("/error-page/500")
    public String errorPage500(HttpServletResponse response, HttpServletRequest request){
        log.info("errorPage 500");
        return "error-page/500";
    }
    @RequestMapping("/error-page/500")
    public String errorPageEx(HttpServletResponse response, HttpServletRequest request){
        log.info("errorPage Ex");
        return "error-page/500";
    }
}
