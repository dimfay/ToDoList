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

    public TasksRestController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public FindTasksResponse findAllTasks(){
        return taskService.findAllTasks(new FindTasksRequest(List.of()));
    }
}
