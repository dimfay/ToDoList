package ru.team.todo;

import ru.team.todo.injections.ApplicationContext;
import ru.team.todo.injections.DIApplicationContextBuilder;
import ru.team.todo.ui.commands.task.AddTaskCommand;
import ru.team.todo.ui.commands.task.DeleteTaskByIdCommand;
import ru.team.todo.ui.commands.task.DeleteTaskByNameCommand;
import ru.team.todo.ui.commands.task.FindTasksCommand;
import ru.team.todo.ui.commands.task.LinkTaskCommand;
import ru.team.todo.ui.commands.task.UnlinkTaskCommand;
import ru.team.todo.ui.commands.user.AddUserCommand;
import ru.team.todo.ui.commands.user.DeleteUserCommand;
import ru.team.todo.ui.commands.user.FindAllUsersCommand;
import ru.team.todo.ui.commands.user.SwitchUserCommand;
import ru.team.todo.ui.Menu;

public class ToDoApplication {

    public static void main(String[] args) {
        ApplicationContext context = new DIApplicationContextBuilder().build("ru.team.todo");

        new Menu()
                .addCommand(context.getBean(AddTaskCommand.class))
                .addCommand(context.getBean(FindTasksCommand.class))
                .addCommand(context.getBean(DeleteTaskByIdCommand.class))
                .addCommand(context.getBean(DeleteTaskByNameCommand.class))
                .addCommand(context.getBean(LinkTaskCommand.class))
                .addCommand(context.getBean(UnlinkTaskCommand.class))
                .addCommand(context.getBean(AddUserCommand.class))
                .addCommand(context.getBean(FindAllUsersCommand.class))
                .addCommand(context.getBean(SwitchUserCommand.class))
                .addCommand(context.getBean(DeleteUserCommand.class))
                .startUI();
    }
}
