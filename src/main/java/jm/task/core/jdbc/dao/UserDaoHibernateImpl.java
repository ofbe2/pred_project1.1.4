package jm.task.core.jdbc.dao;

import com.mysql.cj.Query;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Iterator;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Session session = null;
    SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE user" +
                "(Id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "Name VARCHAR(20) NOT NULL, " +
                "lastName VARCHAR(20) NOT NULL, " +
                "Age INTEGER NOT NULL )")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS user ")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        System.out.println("User с именем – " + name + " добавлен в базу данных");
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.load(User.class,id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> list = session.createQuery("From " + User.class.getSimpleName()).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DELETE FROM user ").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
