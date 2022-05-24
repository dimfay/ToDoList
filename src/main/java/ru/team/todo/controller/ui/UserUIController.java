package ru.team.todo.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.team.todo.dto.users.AddUserRequest;
import ru.team.todo.dto.users.FindUserRequest;
import ru.team.todo.services.UserService;

@Controller
@RequestMapping("/ui")
public class UserUIController {

    private final UserService userService;

    public UserUIController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAllUsers(Model model) {
        var response = this.userService.findAllUsersBy(new FindUserRequest(null));
        model.addAttribute("users", response);
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
