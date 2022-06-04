package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

@Slf4j
class DBConnectionUtilTest {

    @Test
    void connection() {
        Connection connection = DBConnectionUtil.getConnection();
        //다 던짐 일단 URL 정보를 통해 드라이버 얻음 -> 만약 h2가 아닐 경우 다음 드라이버로 확인 절차 넘어감
        //위에서 얻은 드라이버는 java.sql.Connection 인터페이스를 구현하고 있음
        Assertions.assertThat(connection).isNotNull();
    }


}
