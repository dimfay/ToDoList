package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.services.UserService;
import ru.team.todo.ui.commands.Command;

public class DeleteUserCommand extends Command {

    private final UserService service;

    public DeleteUserCommand(UserService service) {
        super("user delete", "Delete the user");
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter username: ");
        String name = scanner.nextLine();

        this.service.removeUser(name);
        System.out.println("User with name '" + name + "' deleted");
    }

}
