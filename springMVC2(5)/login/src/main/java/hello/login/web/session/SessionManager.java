package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "sessionCookieName";
    //세션 Id와 회원 객체를 저장하는 세션 저장소 생성 (동시성 문제로 HashMap대신 사용)
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /*
    * 세선 생성
    * */

    public void createSession(Object value, HttpServletResponse response){
        //세션 id를 생성하고 값을 세션에 저장(UUID를 사용)
        String sessionId = UUID.randomUUID().toString();

        sessionStore.put(sessionId, value);

        //쿠키 생성
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(mySessionCookie);

    }

    //세션조회
    public Object getSession(HttpServletRequest request){
//        Cookie[] cookies = request.getCookies();
//        if(cookies == null){
//            return null;
//        }
//        for (Cookie cookie : cookies) {
//            if(cookie.getName().equals(SESSION_COOKIE_NAME)){
//                return sessionStore.get(cookie.getValue());
//            }
//        }
//        return null;

        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if(sessionCookie == null){
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());
    }

    public Cookie findCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();

        if(cookies == null){
            return null;
        }

        //배열을 스트림으로 바꿔줌
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

    //세션 만료
    public void expire(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if(sessionCookie != null){
            sessionStore.remove(sessionCookie.getValue());
        }
    }


}
