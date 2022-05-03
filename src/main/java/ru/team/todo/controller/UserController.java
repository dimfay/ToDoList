package ru.team.todo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.team.todo.dto.users.AddUserRequest;
import ru.team.todo.dto.users.AddUserResponse;
import ru.team.todo.dto.users.FindUserRequest;
import ru.team.todo.dto.users.FindUserResponse;
import ru.team.todo.services.UserService;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public FindUserResponse findAllUsers() {
        return this.userService.findUsers(new FindUserRequest(List.of()));
    }

    @GetMapping("/users/{name}")
    public FindUserResponse findUserByName(@PathVariable("name") String name) {
        return this.userService.findUsers(new FindUserRequest(List.of(name)));
    }

    @PostMapping("/users")
    public AddUserResponse addUser(@RequestBody AddUserRequest request) {
        return this.userService.addUser(request);
    }

}
