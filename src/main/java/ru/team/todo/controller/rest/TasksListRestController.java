package ru.team.todo.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.team.todo.dto.tasks.*;
import ru.team.todo.services.TaskService;

@RestController
@AllArgsConstructor
public class TasksListRestController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public FindTasksResponse findAllTasks() {
        return taskService.findTask(new FindTasksRequest(null));
    }

    @GetMapping("/users/{username}/tasks")
    public FindTasksResponse findTasksByUser(@PathVariable("username") String username) {
        return taskService.findTask(new FindTasksRequest(username));
    }

}
