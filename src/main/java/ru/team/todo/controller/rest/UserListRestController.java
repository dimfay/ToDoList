package ru.team.todo.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.team.todo.dto.users.*;
import ru.team.todo.services.UserService;

@RequestMapping("/users")
@RestController()
@AllArgsConstructor
public class UserListRestController {
    private final UserService userService;

    @GetMapping
    public FindUserResponse findAllUsers() {
        return this.userService.findUser(new FindUserRequest(null));
    }

    @GetMapping("/{name}")
    public FindUserResponse findUserByName(@PathVariable("name") String name) {
        return this.userService.findUser(new FindUserRequest(name));
    }

}
