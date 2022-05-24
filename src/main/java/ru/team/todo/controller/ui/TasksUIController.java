package ru.team.todo.controller.ui;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.team.todo.dto.tasks.AddTaskRequest;
import ru.team.todo.dto.tasks.FindTasksRequest;
import ru.team.todo.dto.tasks.FindTasksResponse;
import ru.team.todo.services.TaskService;

@Controller
@RequestMapping("/ui")
@AllArgsConstructor
public class TasksUIController {
    private final TaskService taskService;

    @GetMapping("/users/{username}/tasks")
    public String findAllTasks(@PathVariable("username") String username, Model model) {
        FindTasksResponse response =
                taskService.findTask(new FindTasksRequest(username));
        model.addAttribute("tasks", response.getTasks());
        model.addAttribute("user", username);
        return "tasks";
    }

    @GetMapping("/users/{username}/addtask")
    public String addTaskView(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", username);
        model.addAttribute("request", new AddTaskRequest());
        return "addtask";
    }

    @PostMapping("/users/{username}/addtask")
    public String addTask(@PathVariable("username") String username, @ModelAttribute AddTaskRequest request) {
        taskService.addTask(request);
        return "redirect:/ui/users/{username}/tasks";
    }
}
