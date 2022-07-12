package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class UnCheckedTest {
    @Test
    void unchecked_catch(){
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void unchecked_throws(){
        Service service = new Service();
        assertThatThrownBy(()-> service.callThrow())
                .isInstanceOf(MyUnCheckedException.class);
    }




    //RuntimeException을 상속받은 예외는 언체크 예외가 된다.
    static class MyUnCheckedException extends RuntimeException {
        public MyUnCheckedException(String message){
            super(message);
        }
    }

    static class Service{
        Repository repository = new Repository();


        public void callCatch(){
            try {
                repository.call();
            } catch (MyUnCheckedException e){
                log.info("예외 처리, message ={}", e.getMessage(), e);
            }
        }
        public void callThrow(){// throws를 선언하지 않아서 예외를 처리하지 않으면 예외가 또 던져짐
            repository.call();
        }
    }

    static class Repository {
        public void call(){// throws 생략 가능!!
            throw new MyUnCheckedException("ex");
        }
    }


}
