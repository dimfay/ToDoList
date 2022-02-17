package ru.team.todo;

import ru.team.todo.repository.UserRepositoryMemory;
import ru.team.todo.services.ConsoleService;
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
        ConsoleService consoleService = new ConsoleService();
        UserRepositoryMemory userService = new UserRepositoryMemory();
        new Menu()
                .addCommand(new AddTaskCommand(consoleService))
                .addCommand(new FindTasksCommand(consoleService))
                .addCommand(new DeleteTaskByIdCommand(consoleService))
                .addCommand(new DeleteTaskByNameCommand(consoleService))
                .addCommand(new AddUserCommand(userService))
                .addCommand(new FindAllUsers(userService))
                .addCommand(new SwitchUserCommand(userService, consoleService))
                .addCommand(new DeleteUserCommand(userService))
                .startUI();
    }
}
