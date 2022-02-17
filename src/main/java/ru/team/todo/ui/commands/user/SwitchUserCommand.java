package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.repository.UserRepository;
import ru.team.todo.objects.User;
import ru.team.todo.services.ConsoleService;
import ru.team.todo.ui.commands.Command;

public class SwitchUserCommand extends Command {
    private final UserRepository repository;
    private final ConsoleService consoleService;

    public SwitchUserCommand(UserRepository repository, ConsoleService consoleService) {
        super("user switch", "Change user");
        this.repository = repository;
        this.consoleService = consoleService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter username: ");
        String name = scanner.nextLine();

        User user = repository.getUserByName(name);
        if (user == null) {
            System.out.println("User '" + name + "' not found!");
            return;
        }

        consoleService.setSwitchedUser(user);
        System.out.println("You are '" + name + "' now");
    }
}
