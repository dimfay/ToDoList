package ru.team.todo;

import java.util.ArrayList;
import java.util.List;

import ru.team.todo.repository.UserRepository;
import ru.team.todo.repository.UserRepositoryMemory;
import ru.team.todo.services.TaskService;
import ru.team.todo.services.UserService;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.ui.commands.task.AddTaskCommand;
import ru.team.todo.ui.commands.task.DeleteTaskByIdCommand;
import ru.team.todo.ui.commands.task.DeleteTaskByNameCommand;
import ru.team.todo.ui.commands.task.FindTasksCommand;
import ru.team.todo.ui.commands.user.AddUserCommand;
import ru.team.todo.ui.commands.user.DeleteUserCommand;
import ru.team.todo.ui.commands.user.FindAllUsers;
import ru.team.todo.ui.commands.user.SwitchUserCommand;
import ru.team.todo.validation.ValidationService;
import ru.team.todo.validation.rules.MaxTaskDescriptionLength;
import ru.team.todo.validation.rules.MaxTaskNameLengthValidationRule;
import ru.team.todo.validation.rules.MaxUserNameLengthValidationRule;
import ru.team.todo.validation.rules.MinUserNameLengthValidationRule;
import ru.team.todo.validation.rules.ValidationRule;
import ru.team.todo.ui.Menu;

public class ToDoApplication {

    public static void main(String[] args) {
        var consoleService = new ConsoleSession();

        List<ValidationRule> validationRules = new ArrayList<>(List.of(
                new MaxUserNameLengthValidationRule(),
                new MinUserNameLengthValidationRule(),
                new MaxTaskDescriptionLength(),
                new MaxTaskNameLengthValidationRule()
        ));


        var validationService = new ValidationService(validationRules);
        var taskService = new TaskService(consoleService);
        var repository = new UserRepositoryMemory();
        var userService = new UserService(repository, consoleService, validationService);

        new Menu()
                .addCommand(new AddTaskCommand(taskService))
                .addCommand(new FindTasksCommand(consoleService))
                .addCommand(new DeleteTaskByIdCommand(taskService))
                .addCommand(new DeleteTaskByNameCommand(taskService))
                .addCommand(new AddUserCommand(userService))
                .addCommand(new FindAllUsers(repository))
                .addCommand(new SwitchUserCommand(userService))
                .addCommand(new DeleteUserCommand(userService))
                .startUI();
    }
}
