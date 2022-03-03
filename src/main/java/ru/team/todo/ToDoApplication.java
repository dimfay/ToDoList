package ru.team.todo;

import java.util.List;

import ru.team.todo.dto.tasks.AddTaskRequest;
import ru.team.todo.dto.tasks.DeleteTaskByIdRequest;
import ru.team.todo.dto.tasks.DeleteTaskByNameRequest;
import ru.team.todo.dto.tasks.UnlinkTaskRequest;
import ru.team.todo.dto.users.AddUserRequest;
import ru.team.todo.dto.users.RemoveUserRequest;
import ru.team.todo.dto.users.SwitchUserRequest;
import ru.team.todo.repository.UserRepositoryMemory;
import ru.team.todo.services.TaskService;
import ru.team.todo.services.UserService;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.ui.commands.task.*;
import ru.team.todo.ui.commands.user.AddUserCommand;
import ru.team.todo.ui.commands.user.DeleteUserCommand;
import ru.team.todo.ui.commands.user.FindAllUsersCommand;
import ru.team.todo.ui.commands.user.SwitchUserCommand;
import ru.team.todo.validation.ValidationService;
import ru.team.todo.ui.Menu;
import ru.team.todo.validation.rules.task.MaxTaskDescriptionLengthRule;
import ru.team.todo.validation.rules.task.MaxTaskNameLengthRule;
import ru.team.todo.validation.rules.task.WrongIdRule;
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
        var addTaskValidationService = new ValidationService<AddTaskRequest>(List.of(
                new MaxTaskNameLengthRule<>(),
                new MaxTaskDescriptionLengthRule<>()
        ));
        var deleteTaskByNameValidationService = new ValidationService<DeleteTaskByNameRequest>(List.of());
        var deleteTaskByIdValidationService = new ValidationService<DeleteTaskByIdRequest>(List.of(
                new WrongIdRule<>()
        ));

        var taskService = new TaskService(consoleService,
                addTaskValidationService, deleteTaskByIdValidationService,
                deleteTaskByNameValidationService
                );

        var repository = new UserRepositoryMemory();
        var userService = new UserService(repository, consoleService,
                addUserValidationService,
                removeUserValidationService,
                switchUserValidationService,
                findUserValidationService);

        new Menu()
                .addCommand(new AddTaskCommand(taskService))
                .addCommand(new FindTasksCommand(taskService))
                .addCommand(new DeleteTaskByIdCommand(taskService))
                .addCommand(new DeleteTaskByNameCommand(taskService))
                .addCommand(new LinkTaskCommand(taskService))
                .addCommand(new UnlinkTaskCommand(taskService))
                .addCommand(new AddUserCommand(userService))
                .addCommand(new FindAllUsersCommand(userService))
                .addCommand(new SwitchUserCommand(userService))
                .addCommand(new DeleteUserCommand(userService))

                .startUI();
    }
}
