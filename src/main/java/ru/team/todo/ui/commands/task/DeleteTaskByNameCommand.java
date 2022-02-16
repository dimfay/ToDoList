package ru.team.todo.ui.commands.task;

import ru.team.todo.managers.UserService;
import ru.team.todo.objects.User;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

public class DeleteTaskByNameCommand extends Command {

    private final UserService manager;

    public DeleteTaskByNameCommand(UserService manager) {
        super("task delete name", "Delete task by name");
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

        user.deleteTaskByName(name);
        System.out.println("Task with name '" + name + "' deleted");
    }
}
