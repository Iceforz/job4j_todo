package job4j.todo.service;

import job4j.todo.model.Task;
import job4j.todo.store.TaskStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskStore taskStore;

    public Task findById(int id) {
        return taskStore.findTaskById(id);
    }

    public Task add(Task task) {
        return taskStore.addTask(task);
    }

    public void update(Task task) {
       taskStore.replaceTask(task);
    }

    public Object delete(int id) {
        taskStore.deleteTask(id);
        return null;
    }

    public List<Task> findByName(String key) {
        return taskStore.findTaskByName(key);
    }

    public List<Task> findAll() {
        return taskStore.findAllTasks();
    }

    public List<Task> findDone() {
        return taskStore.findTaskByDoneTrue();
    }

    public List<Task> findNotDone() {
        return taskStore.findTaskByDoneFalse();
    }
}