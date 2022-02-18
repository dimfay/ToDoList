package ru.team.todo.ui.commands.task;

import ru.team.todo.objects.User;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

public class DeleteTaskByNameCommand extends Command {

    private final ConsoleSession consoleService;

    public DeleteTaskByNameCommand(ConsoleSession consoleService) {
        super("task delete name", "Delete task by name");
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

        user.deleteTaskByName(name);
        System.out.println("Task with name '" + name + "' deleted");
    }
}
