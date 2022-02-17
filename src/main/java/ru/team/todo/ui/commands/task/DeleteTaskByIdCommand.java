package ru.team.todo.ui.commands.task;

import ru.team.todo.objects.User;
import ru.team.todo.services.ConsoleService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

public class DeleteTaskByIdCommand extends Command {

    private final ConsoleService consoleService;

    public DeleteTaskByIdCommand(ConsoleService consoleService) {
        super("task delete id", "Delete task by id");
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
        System.out.println("Please enter task id: ");
        int id = scanner.nextInt();

        user.deleteTaskById(id);
        System.out.println("Task with id '" + id + "' deleted");
    }
}
