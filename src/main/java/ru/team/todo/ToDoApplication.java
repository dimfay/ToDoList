package ru.team.todo;

import ru.team.todo.managers.UserService;
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

        UserService userService = new UserService();
        new Menu()
                .addCommand(new AddTaskCommand(userService))
                .addCommand(new FindTasksCommand(userService))
                .addCommand(new DeleteTaskByIdCommand(userService))
                .addCommand(new DeleteTaskByNameCommand(userService))
                .addCommand(new AddUserCommand(userService))
                .addCommand(new FindAllUsers(userService))
                .addCommand(new SwitchUserCommand(userService))
                .addCommand(new DeleteUserCommand(userService))
                .startUI();
    }
}
