package job4j.todo.store;

import job4j.todo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserStore  {

    private final CrudRepository crudRepository;

    public Optional<User> addUser(User user) {
        crudRepository.run(session -> session.persist(user));
        return Optional.ofNullable(user);
    }
    public void update(User user) {
        crudRepository.run(session -> session.merge(user));
    }

    public void deleteUser(int id) {
        crudRepository.run(
                "delete from User  where id = :fId",
                Map.of("fId", id));
    }

    public List<User> findUserByName(String key) {
        return crudRepository.query(
                        "from User where name like :fName", User.class,
                Map.of("fName", "%" + key + "%"));
    }

    public Optional<User> findUserByLoginAndPwd(String login, String password) {
        return crudRepository.optional(
                        "from User where login = :fLogin and password = :fPwd", User.class,
                Map.of("fLogin", login , "fPwd", password));
    }

    public Optional<User> findUserById(int id) {
        return crudRepository.optional(
                "from User where id = :fId", User.class,
                        Map.of("fId", id));
    }

    public List<User> findAllUsers() {
        return crudRepository.query(
                "from User", User.class,
                Map.of());
    }
}