package ru.team.todo.controller.ui;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.team.todo.dto.users.AddUserRequest;
import ru.team.todo.dto.users.FindUserRequest;
import ru.team.todo.dto.users.FindUserResponse;
import ru.team.todo.services.UserService;

@Controller
@RequestMapping("/ui")
@AllArgsConstructor
public class UserUIController {
    private final UserService userService;

    @GetMapping("/users")
    public String findAllUsers(Model model) {
        FindUserResponse response = this.userService.findUser(new FindUserRequest(null));
        model.addAttribute("users", response.getUsers());
        return "users";
    }

    @GetMapping("/users/adduser")
    public String addUserView(Model model) {
        model.addAttribute("user", new AddUserRequest());
        return "adduser";
    }

    @PostMapping("/users/adduser")
    public String addUser(@ModelAttribute AddUserRequest request) {
        this.userService.addUser(request);
        return "redirect:/ui/users";
    }

}
