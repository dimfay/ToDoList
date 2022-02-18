package ru.team.todo;

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
import ru.team.todo.ui.Menu;

public class ToDoApplication {

    public static void main(String[] args) {
        ConsoleSession consoleService = new ConsoleSession();
        TaskService taskService = new TaskService(consoleService);
        UserRepository repository = new UserRepositoryMemory();
        UserService userService = new UserService(repository, consoleService);
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
