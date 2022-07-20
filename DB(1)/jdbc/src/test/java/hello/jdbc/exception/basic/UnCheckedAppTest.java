package hello.jdbc.exception.basic;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class UnCheckedAppTest {

    @Test
    void Unchecked(){
        Controller controller = new Controller();
        assertThatThrownBy(()-> controller.request())
                .isInstanceOf(Exception.class);
    }
    @Test
    void printEx(){
        Controller controller = new Controller();
        try {
            controller.request();
        }catch (Exception e){
            // e.printStackTrace(); 옳지 못함
            log.info("ex", e);
        }
    }

    static class Controller{
        Service service = new Service();

        public void request(){
            service.logic();
        }
    }
    static class Service{
        NetworkClient networkClient = new NetworkClient();
        Repository repository = new Repository();

        //해당 예외를 처리하지 않고 controller로 던지는 중중
       public void logic() {
            repository.call();
            networkClient.call();
        }
    }

    static class NetworkClient{
        public void call() {
            throw new RuntimeConnectException("");
        }
    }

    static class Repository{
        public void call() {
            try {
                runSQL();
            } catch (SQLException e){
                throw new RuntimeSQLException(e);
            }

        }

        public void runSQL() throws SQLException{
            throw new SQLException("ex");
        }
    }

    static class RuntimeConnectException extends RuntimeException{
        public RuntimeConnectException(String message){
            super(message);
        }
    }

    static class RuntimeSQLException extends RuntimeException{
        public RuntimeSQLException(Throwable cause) {
            super(cause);
        }
    }


}
