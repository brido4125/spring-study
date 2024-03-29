package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DBConnectionUtil {

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(ConnectionConst.URL, ConnectionConst.USERNAME, ConnectionConst.PASSWORD);
            log.info("Get connection={},class={}", connection, connection.getClass());
            return connection;
        } catch (SQLException e) { //checked exception 처리
            throw new IllegalStateException(e); //runtime exception(unchecked)로 변경
        }
    }
}
