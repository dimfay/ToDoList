package ru.team.todo.controller.rest;


import org.springframework.web.bind.annotation.*;
import ru.team.todo.dto.users.*;
import ru.team.todo.services.UserService;

import java.util.List;

@RequestMapping("/users")
@RestController()
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> findAllUsers(@RequestParam(required = false) String name) {
        var request = new FindUserRequest(name);
        return this.userService.findAllUsersBy(request);
    }

    @GetMapping("/{name}")
    public UserDTO findUserByName(@PathVariable("name") String name) {
        return this.userService.findUserByName(name);
    }

    @PostMapping
    public AddUserResponse addUser(@RequestBody AddUserRequest request) {
        return this.userService.addUser(request);
    }

}
