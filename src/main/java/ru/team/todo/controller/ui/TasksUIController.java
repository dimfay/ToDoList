package ru.team.todo.controller.ui;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.team.todo.dto.tasks.DeleteTaskRequest;
import ru.team.todo.dto.tasks.EditTaskRequest;
import ru.team.todo.services.TaskService;

import javax.validation.Valid;

@Controller
@RequestMapping("/ui/users")
@AllArgsConstructor
public class TasksUIController {
    private final TaskService taskService;

    @PostMapping("{username}/tasks/{taskid}")
    public String taskAction(@PathVariable("username") String username,
                             @PathVariable("taskid") int taskId,
                             @RequestParam(name = "action", defaultValue = "") String action,
                             @ModelAttribute @Valid EditTaskRequest editTaskRequest) {
        if (action.equalsIgnoreCase("delete")) {
            taskService.deleteTask(new DeleteTaskRequest(taskId));
        }
        else if (action.equalsIgnoreCase("edit")) {
            editTaskRequest.setId(taskId);
            taskService.editTask(editTaskRequest);
        }
        return "redirect:/ui/users/{username}";
    }

    @GetMapping("{username}/tasks/{taskid}")
    public String taskEdit(@PathVariable("username") String username,
                           @PathVariable("taskid") int taskId,
                           @RequestParam(name = "action", defaultValue = "") String action, Model model) {
        if (action.equalsIgnoreCase("edit")) {
            model.addAttribute("user", username);
            model.addAttribute("taskid", taskId);
            model.addAttribute("request", new EditTaskRequest());
            return "edittask";
        }
        return "redirect:/ui/users/{username}";
    }

}
