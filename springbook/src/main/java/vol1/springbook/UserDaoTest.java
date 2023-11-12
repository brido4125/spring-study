package vol1.springbook;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vol1.springbook.user.dao.DaoFactory;
import vol1.springbook.user.dao.UserDao;
import vol1.springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = ac.getBean("userDao", UserDao.class);
        dao.add(new User("Hong", "Daebak", "ddd"));
        User user = dao.get("Hong");
    }
}
