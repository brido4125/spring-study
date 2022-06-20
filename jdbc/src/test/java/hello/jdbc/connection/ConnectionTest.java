package hello.jdbc.connection;

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
        //기존의 DriverManager 사용 => 항상 새로운 커넥션 획득
        Connection connection1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection connection2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection : {}, class = {}", connection1, connection1.getClass());
        log.info("connection : {}, class = {}", connection2, connection2.getClass());
    }

    /*
    * DriverManager => 커넥션 획득하려고 할 때마다 디비 인증 정보 넘겨야함
    * DriverManagerDataSource => 한번만 디비 정보 넘기면 getConnection 할때마다 정보 넘길 필요 없음
    * 즉, 설정과 사용이 분리된 구조가 DriverManagerDataSource 이다.
    * */

    @Test
    void dataSourceDriverManager() throws SQLException {
        //DriverManagerDataSource => 항상 새로운 커넥션 획득,다만 스프링에서 제공하는 DriverManager
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);

    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection connection1 = dataSource.getConnection();
        Connection connection2 = dataSource.getConnection();

        log.info("connection : {}, class = {}", connection1, connection1.getClass());
        log.info("connection : {}, class = {}", connection2, connection2.getClass());
    }
}
