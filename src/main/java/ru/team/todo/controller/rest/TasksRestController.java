package ru.team.todo.controller.rest;

import org.springframework.web.bind.annotation.*;
import ru.team.todo.domain.Task;
import ru.team.todo.dto.tasks.*;
import ru.team.todo.dto.users.UserDTO;
import ru.team.todo.services.TaskService;

import java.util.List;

@RestController
public class TasksRestController {
    private final TaskService taskService;

    public TasksRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<TaskDTO> findAllTasks() {
        return taskService.findAllTasks();
    }

    @GetMapping("/users/{username}/tasks")
    public List<TaskDTO> findTasksByUser(@PathVariable("username") String username){
        FindTasksRequest request = new FindTasksRequest(username, List.of());
        var response = taskService.findTasks(request);
        return response.getTasks().stream().map(this::convert).toList();
    }

    @PostMapping("/users/{username}/addtask")
    public AddTaskResponse addTask(@PathVariable("username") String username, @RequestBody AddTaskRequest request) {
        request.setUserName(username);
        return taskService.addTask(request);
    }

    private TaskDTO convert(Task task){
        var userDTO = new UserDTO(task.getUser().getId(), task.getUser().getName());
        return new TaskDTO(task.getId(), userDTO, task.getName(), task.getDescription());
    }

}
