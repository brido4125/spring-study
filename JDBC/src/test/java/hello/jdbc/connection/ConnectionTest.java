package hello.jdbc.connection;

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
        //기존의 JDBC의 DriverManager 사용 => 항상 새로운 커넥션 획득
        Connection connection1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection connection2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        log.info("connection : {}, class = {}", connection1, connection1.getClass());
        log.info("connection : {}, class = {}", connection2, connection2.getClass());
    }

    /*
    * DriverManager => 커넥션 획득하려고 할 때마다 디비 인증 정보 넘겨야함
    * DriverManagerDataSource => 한번만 디비 정보 넘기면 getConnection 할때마다 정보 넘길 필요 없음
    * 즉, 설정과 사용이 분리된 구조가 DriverManagerDataSource 이다.
    * DriverManagerDataSource는 Spring이 제공하는 클래스이다.
    * DriverManagerDataSource는 DataSource의 자식 클래스이다.
    * */

    @Test
    void dataSourceDriverManager() throws SQLException {
        //DriverManagerDataSource => 항상 새로운 커넥션 획득,다만 스프링에서 제공하는 DriverManager
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);

    }


    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        //Connection Pooling by Hikari CP
        //Hikari에서 제공해주는 DataSource 구현체
        HikariDataSource dataSource = new HikariDataSource();
        //설정을 사용에서 하지 않아도 됨
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("Brido's Pool");

        useDataSource(dataSource);
        Thread.sleep(1000);//있고 없고 차이
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        //username, pw 등의 파라미터를 DriverManager와 달리 커넥션을 획득할때마다 넘겨주지 않아도 된다.
        //DataSource는 설정과 사용을 분리시킨 설계이다.
        //반면 DriverManager는 설정과 사용이 합쳐진 설계이다.
        Connection connection1 = dataSource.getConnection();
        Connection connection2 = dataSource.getConnection();

        log.info("connection : {}, class = {}", connection1, connection1.getClass());
        log.info("connection : {}, class = {}", connection2, connection2.getClass());
    }
}
