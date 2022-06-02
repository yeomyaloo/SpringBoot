package hello.exception.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//상태코드 지정 런타임 에러는 원래 500에러
@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "잘못된 요청")
public class BadRequestException extends RuntimeException{
    //상속 메소드를 얘가 해줌
}
