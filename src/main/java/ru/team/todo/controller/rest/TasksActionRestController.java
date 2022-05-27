package ru.team.todo.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.team.todo.dto.tasks.AddTaskRequest;
import ru.team.todo.dto.tasks.AddTaskResponse;
import ru.team.todo.dto.tasks.DeleteTaskRequest;
import ru.team.todo.dto.tasks.DeleteTaskResponse;
import ru.team.todo.dto.tasks.EditTaskRequest;
import ru.team.todo.dto.tasks.EditTaskResponse;
import ru.team.todo.services.TaskService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/action/task")
@RestController()
@AllArgsConstructor
public class TasksActionRestController {
    private final TaskService taskService;

    @PostMapping("/add")
    public AddTaskResponse addTask(@RequestBody @Valid AddTaskRequest request) {
        return taskService.addTask(request);
    }

    @PostMapping("/delete")
    public DeleteTaskResponse deleteTask(@RequestBody @Valid DeleteTaskRequest request) {
        return taskService.deleteTask(request);
    }

    @PostMapping("/edit")
    public EditTaskResponse editTask(@RequestBody @Valid EditTaskRequest request) {
        return taskService.editTask(request);
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
