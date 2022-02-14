package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.managers.UserService;
import ru.team.todo.ui.commands.Command;

public class AddUserCommand extends Command {
	private final UserService manager;

    public AddUserCommand(UserService manager) {
        super("add", "Add new user");
        this.manager = manager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter username: ");
        String name = scanner.nextLine();

        manager.addUser(name);
        System.out.println("User '" + name + "' added");
    }
}
