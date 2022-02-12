package ru.team.todo.ui.commands;

import ru.team.todo.managers.TaskManager;

import java.util.Scanner;

public class AddCommand extends Command {

    private final TaskManager manager;

    public AddCommand(TaskManager manager) {
        super("add", "Add new task");
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
