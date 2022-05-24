package ru.team.todo.controller.rest;

import org.springframework.web.bind.annotation.*;
import ru.team.todo.dto.tasks.*;
import ru.team.todo.services.TaskService;

@RestController
public class TasksRestController {
    private final TaskService taskService;

    public TasksRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public FindTasksResponse findAllTasks() {
        return taskService.findTask(new FindTasksRequest(null));
    }

    @GetMapping("/users/{username}/tasks")
    public FindTasksResponse findTasksByUser(@PathVariable("username") String username) {
        return taskService.findTask(new FindTasksRequest(username));
    }

    @PostMapping("/users/{username}/addtask")
    public AddTaskResponse addTask(@PathVariable("username") String username, @RequestBody AddTaskRequest request) {
        request.setUserName(username);
        return taskService.addTask(request);
    }

}
