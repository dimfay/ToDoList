package ru.team.todo.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.team.todo.dto.CoreError;
import ru.team.todo.dto.users.*;
import ru.team.todo.services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/users")
@RestController()
@AllArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public FindUserResponse findAllUsers() {
        return this.userService.findUser(new FindUserRequest(null));
    }

    @GetMapping("/{name}")
    public FindUserResponse findUserByName(@PathVariable("name") String name) {
        return this.userService.findUser(new FindUserRequest(name));
    }

    @PostMapping
    public AddUserResponse addUser(@RequestBody @Valid AddUserRequest request) {
        return this.userService.addUser(request);
    }

    @DeleteMapping
    public DeleteUserResponse deleteUser(@RequestBody @Valid DeleteUserRequest request) {
        return this.userService.deleteUser(request);
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
