package ru.team.todo.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.team.todo.dto.CoreError;
import ru.team.todo.dto.linkedtasks.FindLinkedTasksRequest;
import ru.team.todo.dto.linkedtasks.FindLinkedTasksResponse;
import ru.team.todo.dto.linkedtasks.LinkTaskRequest;
import ru.team.todo.dto.linkedtasks.LinkTaskResponse;
import ru.team.todo.dto.linkedtasks.UnlinkTaskRequest;
import ru.team.todo.dto.linkedtasks.UnlinkTaskResponse;
import ru.team.todo.services.LinkedTaskService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/linkedtasks")
public class LinkedTasksRestController {
    private final LinkedTaskService linkedTaskService;

    @GetMapping
    public FindLinkedTasksResponse findAllLinkedTasks() {
        return linkedTaskService.findLinkedTasks(new FindLinkedTasksRequest(null));
    }

    @GetMapping("/{id}")
    public FindLinkedTasksResponse findLinkedTasksById(@PathVariable("id") Integer id) {
        return linkedTaskService.findLinkedTasks(new FindLinkedTasksRequest(id));
    }

    @PostMapping
    public LinkTaskResponse linkTasks(@RequestBody @Valid LinkTaskRequest request) {
        return linkedTaskService.linkTask(request);
    }

    @DeleteMapping
    public UnlinkTaskResponse unlinkTasks(@RequestBody @Valid UnlinkTaskRequest request) {
        return linkedTaskService.unlinkTask(request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<CoreError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<CoreError> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new CoreError("Error on field: '" + fieldName + "': " + errorMessage));
        });
        return errors;
    }
}
