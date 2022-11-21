package job4j.todo.controller;

import job4j.todo.model.Task;
import job4j.todo.service.TaskService;
import job4j.todo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/addTask")
    public String addTask(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "addTask";
    }

    @GetMapping("/createTask")
    public String createTask(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "addTask";
    }

    @GetMapping("/formAddTask")
    public String formAddTask(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "addTask";
    }

    @GetMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task) {
        taskService.update(task);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateTask/{taskId}")
    public String formUpdateTask(Model model, @PathVariable("taskId") int id) {
        Optional<Task> opt = taskService.findById(id);
        model.addAttribute("task", opt.get());
        return "updateTask";
    }

    @PostMapping("/deleteTask")
    public String deleteTask(@ModelAttribute Task task) {
        taskService.delete(task.getId());
        return "redirect:/index";
    }

    @GetMapping("/formDeleteTask/{taskId}")
    public String formDeleteTask(Model model, @PathVariable("taskId") int id) {
        model.addAttribute("task", taskService.delete(id));
        return "updateTask";
    }

    @PostMapping("/showTask")
    public String showTask(@ModelAttribute Task task) {
        taskService.findById(task.getId());
        return "redirect:/updateTask";
    }

    @GetMapping("/formShowTask/{taskId}")
    public String formShowTask(Model model, @PathVariable("taskId") int id) {
        Optional<Task> opt = taskService.findById(id);
        model.addAttribute("task", opt.get());
        return "showTask";
    }

    @PostMapping("/setDoneTask")
    public String setDoneTask(@ModelAttribute Task task) {
        task.setDone(true);
        taskService.update(task);
        return "redirect:/index";
    }

    @GetMapping("/formSetDoneTask/{taskId}")
    public String formSetDoneTask(Model model, @PathVariable("taskId") int id) {
        model.addAttribute("task", taskService.findById(id));
        return "done";
    }

    @PostMapping("/createTask")
    public String create(@ModelAttribute Task task, HttpSession session) {
        User user = (User) session.getAttribute("user");
        task.setUser(user);
        task.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        taskService.add(task);
        return "redirect:/index";
    }

    @GetMapping("/notDone")
    public String undone(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
        model.addAttribute("notDone", taskService.findNotDone());
        return "notDone";
    }

    @GetMapping("/done")
    public String done(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
        model.addAttribute("done", taskService.findDone());
        return "done";
    }
}