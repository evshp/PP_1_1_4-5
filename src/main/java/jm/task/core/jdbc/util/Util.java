package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД


    private static final String hostName = "localhost";
    private static final String port = "3306";
    private static final String dataBaseName = "userdb";
    private static final String userName = "root";
    private static final String password = "root";
    public static Connection getConnection () {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://" + hostName + ":" + port + "/" + dataBaseName;
            connection = DriverManager.getConnection(url, userName, password);
            if (!connection.isClosed()) {
                System.out.println("Connected complete with database: " + dataBaseName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
