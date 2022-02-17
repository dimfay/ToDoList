package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.repository.UserRepository;
import ru.team.todo.ui.commands.Command;

public class AddUserCommand extends Command {
    private final UserRepository manager;

    public AddUserCommand(UserRepository manager) {
        super("user add", "Add new user");
        this.manager = manager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter username: ");
        String name = scanner.nextLine();

        manager.addUser(name);
        System.out.println("User with name '" + name + "' added");
    }
}
