package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.managers.UserService;
import ru.team.todo.ui.commands.Command;

public class DeleteUserCommand extends Command {
    private final UserService manager;

    public DeleteUserCommand(UserService manager) {
        super("user delete", "Delete the user");
        this.manager = manager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter username: ");
        String name = scanner.nextLine();

        manager.deleteUser(name);
        System.out.println("User with name '" + name + "' deleted");
    }

}
