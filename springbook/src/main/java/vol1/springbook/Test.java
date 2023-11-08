package vol1.springbook;

import vol1.springbook.user.dao.NUserDao;
import vol1.springbook.user.dao.UserDao;
import vol1.springbook.user.domain.User;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new NUserDao();

        User user = new User();
        user.setId("daeback");
        user.setName("홍대박");
        user.setPassword("hi");

        dao.add(user);

    }
}
