package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.services.UserService;
import ru.team.todo.ui.commands.Command;

public class AddUserCommand extends Command {

    private final UserService service;

    public AddUserCommand(UserService service) {
        super("user add", "Add new user");
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter username: ");
        String name = scanner.nextLine();

        this.service.addUser(name);
        System.out.println("User with name '" + name + "' added");
    }
}
