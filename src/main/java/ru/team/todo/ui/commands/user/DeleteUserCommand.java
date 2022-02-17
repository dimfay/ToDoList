package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.repository.UserRepository;
import ru.team.todo.ui.commands.Command;

public class DeleteUserCommand extends Command {
    private final UserRepository manager;

    public DeleteUserCommand(UserRepository manager) {
        super("user delete", "Delete the user");
        this.manager = manager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter username: ");
        String name = scanner.nextLine();

        manager.removeUser(name);
        System.out.println("User with name '" + name + "' deleted");
    }

}
