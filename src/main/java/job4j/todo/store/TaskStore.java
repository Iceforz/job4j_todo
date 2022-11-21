package job4j.todo.store;

import job4j.todo.model.Task;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStore {
    private final CrudRepository crudRepository;

    public Task addTask(Task task) {
        crudRepository.run(session -> session.persist(task));
        return task;
    }

    public void replaceTask(Task task) {
        crudRepository.run(session -> session.merge(task));
    }

    public void deleteTask(int id) {
       crudRepository.run(
                        "delete from job4j.todo.model.Task t where t.id = :fId",
                Map.of("fId", id));
    }

    public List<Task> findTaskByName(String key) {
       return crudRepository.query(
                        "from job4j.todo.model.Task t where t.name like :fName",
                Task.class,
                Map.of("fName", key));
    }

    public List<Task> findTaskByDoneTrue() {
       return crudRepository.query(
                        "from job4j.todo.model.Task t where t.done is true",
                       Task.class,
                       Map.of());
    }

    public List<Task> findTaskByDoneFalse() {
        return crudRepository.query(
                        "from job4j.todo.model.Task t where t.done is false",
                        Task.class,
                        Map.of());
    }

    public Optional<Task> findTaskById(int id) {
       return crudRepository.optional(
                        "from job4j.todo.model.Task t where t.id = :fId",
                Task.class,
                Map.of("fId", id));
    }

    public List<Task> findAllTasks() {
        return crudRepository.query(
                "from job4j.todo.model.Task",
                Task.class,
                Map.of());
    }
}
