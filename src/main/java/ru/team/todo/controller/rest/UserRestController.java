package ru.team.todo.controller.rest;

import org.springframework.web.bind.annotation.*;
import ru.team.todo.dto.users.*;
import ru.team.todo.services.UserService;

@RequestMapping("/users")
@RestController()
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public FindUserResponse findAllUsers() {
        return this.userService.findUser(new FindUserRequest(null));
    }

    @GetMapping("/{name}")
    public FindUserResponse findUserByName(@PathVariable("name") String name) {
        return this.userService.findUser(new FindUserRequest(name));
    }

    @PostMapping
    public AddUserResponse addUser(@RequestBody AddUserRequest request) {
        return this.userService.addUser(request);
    }

}
