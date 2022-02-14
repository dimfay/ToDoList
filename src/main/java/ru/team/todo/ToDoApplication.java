package ru.team.todo;

import ru.team.todo.managers.TaskService;
import ru.team.todo.managers.UserService;
import ru.team.todo.ui.commands.task.AddCommand;
import ru.team.todo.ui.commands.task.DeleteCommand;
import ru.team.todo.ui.commands.task.FindTasksCommand;
import ru.team.todo.ui.commands.user.AddUserCommand;
import ru.team.todo.ui.commands.user.DeleteUserCommand;
import ru.team.todo.ui.commands.user.FindAllUsers;
import ru.team.todo.ui.commands.user.SwitchUserCommand;
import ru.team.todo.ui.Menu;

public class ToDoApplication {

    public static void main(String[] args) {
        
        UserService userService = new UserService();
        TaskService taskService = new TaskService(userService);
        new Menu(taskService)
                .addTaskCommand(new AddCommand(taskService))
                .addTaskCommand(new FindTasksCommand(taskService))
                .addTaskCommand(new DeleteCommand(taskService))
                .addUserCommand(new AddUserCommand(userService))
                .addUserCommand(new FindAllUsers(userService))
                .addUserCommand(new SwitchUserCommand(userService))
                .addUserCommand(new DeleteUserCommand(userService))
                .startUI();
    }
}
