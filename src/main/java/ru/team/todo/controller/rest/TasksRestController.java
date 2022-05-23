package ru.team.todo.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team.todo.dto.tasks.FindTasksRequest;
import ru.team.todo.dto.tasks.FindTasksResponse;
import ru.team.todo.services.TaskService;

import java.util.List;

@RestController
public class TasksRestController {
    private final TaskService taskService;

    public TasksRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    //TODO Сделать вывод всех тасков или по другому
    @GetMapping("/tasks")
    public FindTasksResponse findAllTasks() {
        return taskService.findTasks(new FindTasksRequest("tmp_user", List.of()));
    }
}
