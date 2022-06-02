package hello.exception.api;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {
    /*컨트롤러 내부에서 터진 예외를 WAS까지 가지고 가지 않아서 컨트롤러에서 처리를 하는데
    * 이는 200 상태코드를 반환하게 한다. 그러나 이를 원하지 않고 http 상태 코드를 다른 것으로
    * 처리하고 싶다면 이때는 @ResponseStatus로 상태코드를 지정해주면 된다.*/
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ErrorResult illegalExHandler(IllegalArgumentException e){
//        log.error("[exceptionHandler] ex", e);
//        return new ErrorResult("BAD", e.getMessage());
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ErrorResult> userExHandler(UserException e){
//        log.error("[exceptionHandler] ex", e);
//        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
//        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
//
//    }
//
//    //최상위 예외이기 때문에 다른 특정 예외가 처리가 안 된다면 Exception 처리에서 다 처리해준다.
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler
//    public ErrorResult exHandler(Exception e){
//        log.error("[ExceptionHandler] ex", e);
//        return new ErrorResult("EX", "내부 오류");
//    }

    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id){
        if(id.equals("ex")){
            throw new RuntimeException("잘못된 사용자");
        }
        if(id.equals("bad")){
            throw new IllegalArgumentException("잘못된 입력 값");
        }

        if(id.equals("user-ex")){
            throw new UserException("사용자 오류");
        }
        return new MemberDto(id, "hello" + id);
    }
    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String memberId;
        private String name;
    }
}
