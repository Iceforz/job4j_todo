package job4j.todo.controller;

import job4j.todo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DoneController {

    private final TaskService taskService;

    @GetMapping("/done")
    public String done(Model model) {
        model.addAttribute("done", taskService.findDone());
        return "done";
    }
}
