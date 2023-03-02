package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.service.ServiceRegistry;
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

    public static Connection getConnection() {
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


    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://" + hostName + ":" + port + "/" + dataBaseName)
                .setProperty("hibernate.connection.username", userName)
                .setProperty("hibernate.connection.password", password)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.hbm2ddl.auto", "create-drop")
                .addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }






}
