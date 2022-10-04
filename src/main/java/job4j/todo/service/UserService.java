package job4j.todo.service;

import job4j.todo.model.User;
import job4j.todo.store.UserStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserStore userStore;

    public Optional<User> add(User user) {
        return userStore.addUser(user);
    }

    public Optional<User> findUserByLoginAndPwd(String login, String password) {
        return userStore.findUserByLoginAndPwd(login, password);
    }

    public List<User> findAllUsers() {
        return userStore.findAllUsers();
    }

    public Optional<User> findUserById(int id) {
        return userStore.findUserById(id);
    }

}