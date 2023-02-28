package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final static Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String tableCreate = "CREATE TABLE IF NOT EXISTS users (\n" +
                             "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                             "name VARCHAR(50) NOT NULL,\n" +
                             "lastName VARCHAR(50) NOT NULL,\n" +
                             "age INT NOT NULL \n" +
                             ");";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(tableCreate);
            System.out.println("Таблица users создана");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS users;";
        try (Statement statement = connection.createStatement()) {
                statement.execute(dropTable);
                System.out.println("Таблица удаленна");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO users (name, lastname, age) " +
                          "VALUES (?, ?, ?)";
        if (name.length() <= 50 & lastName.length() <= 50) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(saveUser)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setInt(3, age);
                preparedStatement.executeUpdate();
                System.out.println("User c именем - " + name + " добавлен в базу данных");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Длина имени и фамилии не может превышать 50 символов");
        }
    }

    public void removeUserById(long id) {
        String removeUserById = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeUserById)) {
            preparedStatement.setLong(1, id);
            int count = preparedStatement.executeUpdate();
            System.out.println("Строка с id =" + id + " была удаленна.\n" +
                               "Всего измененых строк " + count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List <User> users = new ArrayList<>();
        String getAllUsers = "SELECT * FROM users";

        try(Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(getAllUsers)) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong(1);
                    String name = resultSet.getString("name");
                    String lastname = resultSet.getString("lastName");
                    byte age = resultSet.getByte("age");
                    users.add(new User(id, name, lastname, age));
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Пользователи получены");

        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users;");
            System.out.println("Таблица очищена");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
