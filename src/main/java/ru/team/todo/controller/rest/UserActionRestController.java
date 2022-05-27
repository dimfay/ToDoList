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
import ru.team.todo.dto.users.AddUserRequest;
import ru.team.todo.dto.users.DeleteUserRequest;
import ru.team.todo.services.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/action/user")
@RestController()
@AllArgsConstructor
public class UserActionRestController {
    private final UserService userService;

    @PostMapping("/add")
    public Object addUser(@RequestBody @Valid AddUserRequest request) {
        return this.userService.addUser(request);
    }

    @PostMapping("/delete")
    public Object deleteUser(@RequestBody @Valid DeleteUserRequest request) {
        return this.userService.deleteUser(request);
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
