package vol1.springbook;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vol1.springbook.user.dao.CountingConnectionMaker;
import vol1.springbook.user.dao.CountingDaoFactory;
import vol1.springbook.user.dao.UserDao;
import vol1.springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoConnectionCountingTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao dao = ac.getBean("userDao", UserDao.class);

        dao.add(new User("12", "daeback", "hcshcs"));
        dao.add(new User("13", "daeback1", "hcshcs2"));
        dao.add(new User("14", "daeback2", "hcshcs2"));

        CountingConnectionMaker ccm = ac.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("ccm.getCounter() = " + ccm.getCounter());
    }
}
