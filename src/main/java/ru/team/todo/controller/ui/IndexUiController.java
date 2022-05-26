package ru.team.todo.controller.ui;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import ru.team.todo.dto.users.FindUserRequest;
import ru.team.todo.dto.users.FindUserResponse;
import ru.team.todo.services.UserService;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class IndexUiController {

    private final UserService userService;

    @GetMapping()
    public String indexView(Model model) {
        FindUserResponse response = this.userService.findUser(new FindUserRequest(null));
        model.addAttribute("users", response.getUsers());
        return "index";
    }

}
