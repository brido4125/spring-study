package vol1.springbook;

import org.junit.jupiter.api.Test;
import vol1.springbook.user.dao.DaoFactory;
import vol1.springbook.user.dao.SimpleConnectionMaker;
import vol1.springbook.user.dao.UserDao;
import vol1.springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    @Test
    void main() throws SQLException, ClassNotFoundException {
        UserDao userDao = new DaoFactory().userDao();
        userDao.add(new User());
    }
}
