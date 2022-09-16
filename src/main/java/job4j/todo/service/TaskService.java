package job4j.todo.service;

import job4j.todo.model.Task;
import job4j.todo.store.TaskStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskStore store;

    public Task findById(int id) {
        return store.findTaskById(id);
    }

    public Task add(Task task) {
        return store.addTask(task);
    }

    public boolean update(Task task) {
        return store.replaceTask(task);
    }

    public boolean delete(int id) {
        return store.deleteTask(id);
    }

    public List<Task> findByName(String key) {
        return store.findTaskByName(key);
    }

    public List<Task> findAll() {
        return store.findAllTasks();
    }

    public List<Task> findDone() {
        return store.findTaskByDoneTrue();
    }

    public List<Task> findNotDone() {
        return store.findTaskByDoneFalse();
    }
}