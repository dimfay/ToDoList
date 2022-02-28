package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.dto.users.AddUserRequest;
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

        var request = new AddUserRequest(name);
        var response = service.addUser(request);
        System.out.println("Received response: " + response);
        if (!response.getErrors().isEmpty()) {
            return;
        }
        System.out.println("User with name '" + name + "' added");
    }
}
