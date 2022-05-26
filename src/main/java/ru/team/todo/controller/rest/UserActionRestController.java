package ru.team.todo.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team.todo.dto.users.AddUserRequest;
import ru.team.todo.dto.users.DeleteUserRequest;
import ru.team.todo.services.UserService;

@RequestMapping("/action/user")
@RestController()
@AllArgsConstructor
public class UserActionRestController {
    private final UserService userService;

    @PostMapping("/add")
    public Object addUser(@RequestBody AddUserRequest request) {
        return this.userService.addUser(request);
    }

    @PostMapping("/delete")
    public Object deleteUser(@RequestBody DeleteUserRequest request) {
        return this.userService.deleteUser(request);
    }

}
