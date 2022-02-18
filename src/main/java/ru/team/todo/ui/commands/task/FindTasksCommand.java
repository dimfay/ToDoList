package ru.team.todo.ui.commands.task;

import ru.team.todo.objects.Task;
import ru.team.todo.objects.User;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.ui.commands.Command;

import java.util.Collection;

public class FindTasksCommand extends Command {

    //TODO Скорее всего использовать здесь сервис
    private final ConsoleSession consoleService;

    public FindTasksCommand(ConsoleSession consoleService) {
        super("task find", "Display all tasks");
        this.consoleService = consoleService;
    }

    @Override
    public void execute() {
        User user = this.consoleService.getSwitchedUser();
        if (user == null) {
            System.out.println("No user selected.");
            return;
        }

        Collection<Task> tasks = user.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("Tasks not found!");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }
}
