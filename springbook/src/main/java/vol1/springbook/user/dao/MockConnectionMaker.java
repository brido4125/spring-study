package vol1.springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class MockConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
        return null;
    }
}
