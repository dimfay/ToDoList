package ru.team.todo.ui.commands.task;

import ru.team.todo.managers.TaskService;
import ru.team.todo.objects.Task;
import ru.team.todo.ui.commands.Command;

import java.util.Collection;
import java.util.Scanner;

public class FindTasksCommand extends Command {

    private final TaskService manager;

    public FindTasksCommand(TaskService manager) {
        super("find", "Display all tasks");
        this.manager = manager;
    }

    @Override
    public void execute() {
        Collection<Task> tasks = this.manager.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("Tasks not found!");
            informMsg();
            return;
        }
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
        informMsg();
    }

    private static void informMsg() {
        System.out.println("Enter any key to continue");
        new Scanner(System.in).nextLine();
    }
}
