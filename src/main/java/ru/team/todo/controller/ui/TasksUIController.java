package ru.team.todo.controller.ui;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.team.todo.dto.tasks.AddTaskRequest;

import ru.team.todo.dto.tasks.DeleteTaskRequest;
import ru.team.todo.services.TaskService;

@Controller
@RequestMapping("/ui/users")
@AllArgsConstructor
public class TasksUIController {
    private final TaskService taskService;

    @PostMapping("{username}")
    public String userView(@PathVariable("username") String username,
                           @RequestParam(name = "action", defaultValue = "") String action,
                           @ModelAttribute AddTaskRequest request) {
        if (action.equalsIgnoreCase("newtask")) {
            taskService.addTask(request);
        }
        return "redirect:/ui/users/{username}";
    }

    @GetMapping("{username}/{taskid}")
    public String taskView(@PathVariable("username") String username,
                           @PathVariable("taskid") int taskId,
                           @RequestParam(name = "action", defaultValue = "") String action) {
        if (action.equalsIgnoreCase("delete")) {
            taskService.deleteTask(new DeleteTaskRequest(taskId));
        }
        return "redirect:/ui/users/{username}";
    }

}
