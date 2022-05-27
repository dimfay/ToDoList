package ru.team.todo.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.team.todo.dto.linkedtasks.*;
import ru.team.todo.services.LinkedTaskService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/action/linkedtasks")
@AllArgsConstructor
public class LinkedTasksActionController {
    private final LinkedTaskService linkedTaskService;

    @PostMapping("/link")
    public LinkTaskResponse linkTasks(@RequestBody @Valid LinkTaskRequest request){
        return linkedTaskService.linkTask(request);
    }

    @PostMapping("/unlink")
    public UnlinkTaskResponse unlinkTasks(@RequestBody @Valid UnlinkTaskRequest request){
        return linkedTaskService.unlinkTask(request);
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
