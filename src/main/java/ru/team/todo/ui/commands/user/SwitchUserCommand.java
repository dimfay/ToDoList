package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.services.UserService;
import ru.team.todo.ui.commands.Command;

public class SwitchUserCommand extends Command {

    private final UserService service;

    public SwitchUserCommand(UserService service) {
        super("user switch", "Change user");
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter username: ");
        String name = scanner.nextLine();

        this.service.switchUser(name);
        System.out.println("You are '" + name + "' now");
    }
}
