package ru.team.todo.ui.commands.task;

import ru.team.todo.objects.User;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

public class AddTaskCommand extends Command {

    private final ConsoleSession consoleService;

    public AddTaskCommand(ConsoleSession consoleService) {
        super("task add", "Add new task");
        this.consoleService = consoleService;
    }

    @Override
    public void execute() {
        User user = this.consoleService.getSwitchedUser();
        if (user == null) {
            System.out.println("No user selected.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter task name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter task description: ");
        String description = scanner.nextLine();

        user.addTask(name, description);
        System.out.println("Task '" + name + "' added");
    }
}
