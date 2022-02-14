package ru.team.todo.ui.commands.task;

import ru.team.todo.managers.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

public class DeleteCommand extends Command {

    private final TaskService manager;

    public DeleteCommand(TaskService manager) {
        super("delete", "Delete task");
        this.manager = manager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter todo name: ");
        String name = scanner.nextLine();

        this.manager.deleteTaskByName(name);
        System.out.println("Task '" + name + "' deleted");
    }
}
