package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    private final static SessionFactory sessionFactory = Util.getSessionFactory();
    private UserDao userDaoJDBC = new UserDaoJDBCImpl();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            List<User> users = (List<User>) session.createQuery("FROM User").list();
            return users;

        }  catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

    }


}
