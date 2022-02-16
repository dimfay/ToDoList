package ru.team.todo.ui.commands.task;

import ru.team.todo.managers.UserService;
import ru.team.todo.objects.User;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

public class DeleteTaskByIdCommand extends Command {

    private final UserService manager;

    public DeleteTaskByIdCommand(UserService manager) {
        super("task delete id", "Delete task by id");
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
        System.out.println("Please enter task id: ");
        int id = scanner.nextInt();

        user.deleteTaskById(id);
        System.out.println("Task with id '" + id + "' deleted");
    }
}
