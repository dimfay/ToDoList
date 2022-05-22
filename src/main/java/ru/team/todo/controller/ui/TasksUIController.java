package ru.team.todo.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.team.todo.dto.tasks.AddTaskRequest;
import ru.team.todo.dto.tasks.FindTasksRequest;
import ru.team.todo.dto.tasks.FindTasksResponse;
import ru.team.todo.dto.users.FindUserRequest;
import ru.team.todo.services.TaskService;
import ru.team.todo.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class TasksUIController {
    private final TaskService taskService;
    private final UserService userService;

    public TasksUIController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/users/{username}/tasks")
    public String findAllTasks(@PathVariable("username") String username, Model model) {
        var findUserResponse = userService.findUsers(new FindUserRequest(List.of(username)));

        FindTasksResponse response =
                taskService.findTasks(new FindTasksRequest(username, List.of()));
        model.addAttribute("tasks", response.getTasks());
        model.addAttribute("user", username);
        return "tasks";
    }

    @GetMapping("/users/{username}/addtask")
    public String addTaskView(@PathVariable("username") String username, Model model) {
        var findUserResponse = userService.findUsers(new FindUserRequest(List.of(username)));
        model.addAttribute("user", findUserResponse.getUsers().get(0));
        model.addAttribute("task", new AddTaskRequest());
        return "addtask";
    }

    @PostMapping("/users/{username}/addtask")
    public String addTask(@PathVariable("username") String username, @ModelAttribute AddTaskRequest request) {
        taskService.addTask(request);
        return "redirect:/ui/users/{username}/tasks";
    }
}
