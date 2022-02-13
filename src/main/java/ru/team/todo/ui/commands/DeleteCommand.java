package ru.team.todo.ui.commands;

import ru.team.todo.managers.TaskManager;

import java.util.Scanner;

public class DeleteCommand extends Command {

    private final TaskManager manager;

    public DeleteCommand(TaskManager manager) {
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
