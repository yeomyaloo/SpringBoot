package hello.jdbc.exception.basic;


import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {

    @Test
    void checked_catch(){
        //레포지토리에서 예외 발생! -> Service에서는 이 예외를 처리 -> 정상흐름 반환
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_Throw() throws MyCheckedException {
        /*예외를 잡지 않고 서비스에서 던지면? -> 정상 흐름을 반환 X
         해당 예외가 레포지토리 계층에서 발생!!
         -> 서비스 계층에서 예외가 발생한 메소드를 사용!! -> 예외를 처리하지 않고 던짐
         -> 이때 main 쓰레드를 그냥 사용하는 경우라면 서버가 종료되지만 그런 경우가 아니라면(웹애플리케이션 계층의 경우) WAS가 처리!
         = 이 경우엔 예외를 잡거나 던지거나 해야 한다!!!!!
        */

        Service service = new Service();

        //이 로직을 호출하면 이 예외가 터져야 한다는 것을 검증하는 테스트 코드
        Assertions.assertThatThrownBy(()-> service.callThrow())
                .isInstanceOf(MyCheckedException.class);

    }
    static class MyCheckedException extends Exception{
        public MyCheckedException(String message){
            super(message);
        }
    }
    //서비스에서 checked 예외는 예외를 잡아 처리, 던지는 방식을 선택해야 한다
    static class Service{
        Repository repository =new Repository();

        //예외를 잡아서 처리하는 코드
        public void callCatch(){
            try {
                repository.call();
            } catch (MyCheckedException e) {
                //예외 처리 로직
                log.info("예외 처리, message = {}", e.getMessage(), e);
            }
        }

        public void callThrow() throws MyCheckedException {
            repository.call();
        }

    }

    static class Repository{
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }

}
