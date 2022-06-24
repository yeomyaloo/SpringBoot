package hello.jdbc.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        log.info("connection={}, class={}",con1, con2.getClass());
        log.info("connection={}, class={}",con2, con2.getClass());
    }

    @Test
    void dataSourceDriveManager() throws SQLException {
        //DriveManagerDataSource - 항상 새로운 커넥션을 획득
        //설정과 사용의 분리!
        //설정부(나중에 설정 바꾸면 이부분만 바꾸면 된다.,)
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

        //사용부
        useDataSource(dataSource);
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);//기본 10으로 설정한다고 한다.
        dataSource.setPoolName("MyPool");//Pool 이름도 정해줄 수 있다.

        useDataSource(dataSource);
        /*별도의 쓰레드에서 pool 추가 작업을 진행하기 때문에 빨라서 로그로 안 남을 수
        * 있기 때문에 이를 남기기 위한 코드!*/
        Thread.sleep(1000);

    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();

        log.info("connection={}, class={}",con1, con2.getClass());
        log.info("connection={}, class={}",con2, con2.getClass());
    }




}
