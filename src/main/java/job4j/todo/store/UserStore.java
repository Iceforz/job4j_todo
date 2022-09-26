package job4j.todo.store;

import job4j.todo.model.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserStore implements AutoCloseable {

    private final SessionFactory sf;

    public Optional<User> addUser(User user) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return Optional.of(user);
    }

    public boolean replaceUser(User user) {
        Session session = sf.openSession();
        session.beginTransaction();
        int result = session.createQuery(
                        "update job4j.todo.model.User t set t.name = :newName, t.login = :newLogin, t.password = :newPass where t.id = :fId")
                .setParameter("newName", user.getName())
                .setParameter("newLogin", user.getLogin())
                .setParameter("newPass", user.getPassword())
                .setParameter("fId", user.getId())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result > 0;
    }

    public boolean deleteUser(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        int result = session.createQuery(
                        "delete from job4j.todo.model.User t where t.id = :fId")
                .setParameter("fId", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result > 0;
    }

    public List<User> findUserByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<User> result = session.createQuery(
                        "from job4j.todo.model.User t where t.name like :fName")
                .setParameter("fName", key)
                .list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public Optional<User> findUserByLoginAndPwd(String login, String password) {
        Session session = sf.openSession();
        session.beginTransaction();
        User result = (User) session.createQuery(
                        "from job4j.todo.model.User t where t.login = :fLogin and t.password = :fPwd")
                .setParameter("fLogin", login)
                .setParameter("fPwd", password)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return Optional.of(result);
    }

    public Optional<User> findUserById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        User result = (User) session.createQuery(
                        "from job4j.todo.model.User t where t.id = :fId")
                .setParameter("fId", id)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return Optional.of(result);
    }

    public List<User> findAllUsers() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<User> result = session.createQuery(
                "from job4j.todo.model.User"
        ).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void close() {
        sf.close();
    }
}