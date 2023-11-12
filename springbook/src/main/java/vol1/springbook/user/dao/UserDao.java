package vol1.springbook.user.dao;

import vol1.springbook.user.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static UserDao INSTANCE;

    private final ConnectionMaker simpleConnectionMaker;

    private UserDao(ConnectionMaker connectionMaker) {
        this.simpleConnectionMaker = connectionMaker;
    }

    public static synchronized UserDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDao(??);
        }
        return INSTANCE;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = simpleConnectionMaker.makeNewConnection();
        PreparedStatement ps = connection.prepareStatement(
            "insert into users(id, name, password) values (?, ?, ?)");

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection connection = simpleConnectionMaker.makeNewConnection();
        PreparedStatement ps = connection.prepareStatement(
            "select * from users where id = ?");
        ps.setString(1, id);

        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        User user = new User(resultSet.getString("id"),
            resultSet.getString("name"),
            resultSet.getString("password"));
        resultSet.close();
        ps.close();
        connection.close();

        return user;
    }
}
