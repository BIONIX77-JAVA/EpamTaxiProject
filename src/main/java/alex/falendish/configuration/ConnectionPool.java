package alex.falendish.configuration;

import java.sql.Connection;

public interface ConnectionPool {

    Connection getConnection();

    boolean releaseConnection(Connection connection);
}
