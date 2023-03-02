package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao getUserDaoHibernate = new UserDaoHibernateImpl();


    public UserServiceImpl() {

    }

    public void createUsersTable() {
        getUserDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        getUserDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        getUserDaoHibernate.saveUser(name, lastName, (byte) age);
    }

    public void removeUserById(long id) {
        getUserDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return getUserDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        getUserDaoHibernate.cleanUsersTable();
    }
}
