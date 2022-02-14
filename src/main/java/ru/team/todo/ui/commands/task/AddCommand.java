package ru.team.todo.ui.commands.task;

import ru.team.todo.managers.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

public class AddCommand extends Command {

    private final TaskService manager;

    public AddCommand(TaskService manager) {
        super("add task", "Add new task");
        this.manager = manager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter todo name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter todo description: ");
        String description = scanner.nextLine();

        this.manager.addTask(name, description);
        System.out.println("Task '" + name + "' added");
    }
}
