package ru.team.todo.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.team.todo.dto.tasks.*;
import ru.team.todo.services.TaskService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class TasksRestController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public FindTasksResponse findAllTasks() {
        return taskService.findTask(new FindTasksRequest(null));
    }

    @PostMapping("/tasks")
    public AddTaskResponse addTask(@RequestBody @Valid AddTaskRequest request) {
        return taskService.addTask(request);
    }

    @DeleteMapping("/tasks")
    public DeleteTaskResponse deleteTask(@RequestBody @Valid DeleteTaskRequest request) {
        return taskService.deleteTask(request);
    }

    @PutMapping("/tasks")
    public EditTaskResponse editTask(@RequestBody @Valid EditTaskRequest request) {
        return taskService.editTask(request);
    }

    @GetMapping("/users/{username}/tasks")
    public FindTasksResponse findTasksByUser(@PathVariable("username") String username) {
        return taskService.findTask(new FindTasksRequest(username));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
