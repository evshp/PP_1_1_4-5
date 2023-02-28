package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
            userService.createUsersTable();
            userService.saveUser("Will", "Smith", (byte) 54);
            userService.saveUser("Tim", "Berton", (byte) 44);
            userService.saveUser("James", "Marshall", (byte) 34);
            userService.saveUser("James", "Din", (byte) 24);
            System.out.println(userService.getAllUsers());
            userService.cleanUsersTable();
            userService.dropUsersTable();

    }
}
