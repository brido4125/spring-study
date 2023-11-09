package vol1.springbook;

import org.junit.jupiter.api.Test;
import vol1.springbook.user.dao.SimpleConnectionMaker;
import vol1.springbook.user.dao.UserDao;
import vol1.springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    @Test
    void main() throws SQLException, ClassNotFoundException {
        UserDao dao = new UserDao(new SimpleConnectionMaker());

        User user = new User();
        user.setId("daeback");
        user.setName("홍대박");
        user.setPassword("hi");

        dao.add(user);
    }
}
