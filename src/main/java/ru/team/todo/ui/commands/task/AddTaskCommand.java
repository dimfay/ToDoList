package ru.team.todo.ui.commands.task;

import ru.team.todo.managers.UserService;
import ru.team.todo.objects.User;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

public class AddTaskCommand extends Command {

    private final UserService manager;

    public AddTaskCommand(UserService manager) {
        super("task add", "Add new task");
        this.manager = manager;
    }

    @Override
    public void execute() {
        User user = manager.getCurrentUser();
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
