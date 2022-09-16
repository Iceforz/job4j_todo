package job4j.todo.controller;

import job4j.todo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class NotDoneController {
    private final TaskService taskService;

    @GetMapping("/notDone")
    public String notDone(Model model) {
        model.addAttribute("notDone", taskService.findNotDone());
        return "notDone";
    }
}
