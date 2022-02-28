package ru.team.todo;

import java.util.List;

import ru.team.todo.dto.users.AddUserRequest;
import ru.team.todo.dto.users.RemoveUserRequest;
import ru.team.todo.dto.users.SwitchUserRequest;
import ru.team.todo.repository.UserRepositoryMemory;
import ru.team.todo.services.TaskService;
import ru.team.todo.services.UserService;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.ui.commands.task.AddTaskCommand;
import ru.team.todo.ui.commands.task.DeleteTaskByIdCommand;
import ru.team.todo.ui.commands.task.DeleteTaskByNameCommand;
import ru.team.todo.ui.commands.task.FindTasksCommand;
import ru.team.todo.ui.commands.task.LinkTaskCommand;
import ru.team.todo.ui.commands.user.AddUserCommand;
import ru.team.todo.ui.commands.user.DeleteUserCommand;
import ru.team.todo.ui.commands.user.FindAllUsers;
import ru.team.todo.ui.commands.user.SwitchUserCommand;
import ru.team.todo.validation.ValidationService;
import ru.team.todo.ui.Menu;
import ru.team.todo.validation.rules.users.FindUserNameLengthRule;
import ru.team.todo.validation.rules.users.UserNameLengthRule;

public class ToDoApplication {

    public static void main(String[] args) {
        var consoleService = new ConsoleSession();

        var addUserValidationService = new ValidationService<AddUserRequest>(List.of(
                new UserNameLengthRule<>()
        ));
        var removeUserValidationService = new ValidationService<RemoveUserRequest>(List.of(
                new UserNameLengthRule<>()
        ));
        var switchUserValidationService = new ValidationService<SwitchUserRequest>(List.of(
                new UserNameLengthRule<>()
        ));
        var findUserValidationService = new ValidationService<>(List.of(
                new FindUserNameLengthRule()
        ));

        var taskService = new TaskService(consoleService);
        var repository = new UserRepositoryMemory();
        var userService = new UserService(repository, consoleService, addUserValidationService, removeUserValidationService, switchUserValidationService, findUserValidationService);

        new Menu()
                .addCommand(new AddTaskCommand(taskService))
                .addCommand(new FindTasksCommand(taskService))
                .addCommand(new DeleteTaskByIdCommand(taskService))
                .addCommand(new DeleteTaskByNameCommand(taskService))
                .addCommand(new LinkTaskCommand(taskService))
                .addCommand(new AddUserCommand(userService))
                .addCommand(new FindAllUsers(userService))
                .addCommand(new SwitchUserCommand(userService))
                .addCommand(new DeleteUserCommand(userService))
                .startUI();
    }
}
