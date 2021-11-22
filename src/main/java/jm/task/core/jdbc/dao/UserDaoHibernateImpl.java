package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(90) NULL, " +
                "lastName VARCHAR(90) NULL, " +
                "age TINYINT NULL, " +
                "PRIMARY KEY (`id`), " +
                "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)";

        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS users";

        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "INSERT INTO users (name, lastName, age) VALUES(:name,:lastName,:age)";

        NativeQuery query = session.createSQLQuery(sql).addEntity(User.class);
        query.setParameter("name", name);
        query.setParameter("lastName", lastName);
        query.setParameter("age", age);

        query.executeUpdate();
        System.out.println("User с именем – " + name + " добавлен в базу данных");

        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "DELETE FROM users WHERE id = :id";

        NativeQuery query = session.createSQLQuery(sql).addEntity(User.class);
        query.setParameter("id", id);

        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "SELECT id, name, lastName, age FROM users";

        List<User> users = session.createNativeQuery(sql).addEntity(User.class).list();
        System.out.println(users.toString());

        transaction.commit();
        session.close();

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "TRUNCATE TABLE users";

        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();

        transaction.commit();
        session.close();
    }
}
