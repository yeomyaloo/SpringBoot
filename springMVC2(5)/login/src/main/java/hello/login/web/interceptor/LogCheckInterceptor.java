package hello.login.web.interceptor;

import hello.login.web.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LogCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터셉터 실행 {}",requestURI);

        HttpSession session = request.getSession();

        if (session==null || session.getAttribute(SessionConst.LOGIN_MEMBER)==null){
            log.info("미인증 사용자 요철");
            //로그인 화면으로 리다이렉트 시켜주면 된다.
            response.sendRedirect("/login?redirectURL="+requestURI);
            return false;//여기서 false를 돌려주는 이유는 로그인 하지 않은 사용자가 다른 컨트롤러를 호출하지 못하게 하기 위함이다.
        }

        return true;

    }
}
