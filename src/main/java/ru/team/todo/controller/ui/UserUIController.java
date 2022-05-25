package ru.team.todo.controller.ui;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.team.todo.dto.tasks.AddTaskRequest;
import ru.team.todo.dto.tasks.DeleteTaskRequest;
import ru.team.todo.dto.tasks.EditTaskRequest;
import ru.team.todo.dto.tasks.FindTasksRequest;
import ru.team.todo.dto.tasks.FindTasksResponse;
import ru.team.todo.dto.users.AddUserRequest;
import ru.team.todo.dto.users.FindUserRequest;
import ru.team.todo.dto.users.FindUserResponse;
import ru.team.todo.dto.users.DeleteUserRequest;
import ru.team.todo.services.TaskService;
import ru.team.todo.services.UserService;

@Controller
@RequestMapping("/ui/users")
@AllArgsConstructor
public class UserUIController {
    private final UserService userService;
    private final TaskService taskService;

    @GetMapping()
    public String addUserView(@RequestParam(name = "action", defaultValue = "") String action, Model model) {
        FindUserResponse response = this.userService.findUser(new FindUserRequest(null));
        model.addAttribute("users", response.getUsers());
        if (action.equalsIgnoreCase("newuser")) {
            model.addAttribute("request", new AddUserRequest());
            return "newuser";
        }
        return "index";
    }

    @PostMapping()
    public String addUser(@RequestParam(name = "action", defaultValue = "") String action,
                          @ModelAttribute AddUserRequest request) {
        if (action.equalsIgnoreCase("newuser")) {
            this.userService.addUser(request);
        }
        return "redirect:/ui/users";
    }

    @GetMapping("{username}")
    public String userView(@PathVariable("username") String username,
                           @RequestParam(name = "action", defaultValue = "") String action, Model model) {
        if (action.equalsIgnoreCase("newtask")) {
            model.addAttribute("user", username);
            model.addAttribute("request", new AddTaskRequest());
            return "newtask";
        }

        FindTasksResponse response =
                taskService.findTask(new FindTasksRequest(username));
        model.addAttribute("tasks", response.getTasks());
        model.addAttribute("user", username);
        model.addAttribute("deleteuser", new DeleteUserRequest());
        model.addAttribute("deleterequest", new DeleteTaskRequest());
        return "user";
    }

    @PostMapping("{username}")
    public String userAction(@PathVariable("username") String username,
                             @RequestParam(name = "action", defaultValue = "") String action,
                             @ModelAttribute AddTaskRequest addTaskRequest) {
        if (action.equalsIgnoreCase("delete")) {
            this.userService.removeUser(new DeleteUserRequest(username));
        }
        else if (action.equalsIgnoreCase("newtask")) {
            addTaskRequest.setUserName(username);
            this.taskService.addTask(addTaskRequest);
            return "redirect:/ui/users/{username}";
        }
        return "redirect:/ui/users";
    }

}
