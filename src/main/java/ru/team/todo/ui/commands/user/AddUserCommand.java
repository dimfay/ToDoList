package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.dto.AddUserRequest;
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

        var request = new AddUserRequest();
        request.setName(name);
        var response = service.addUser(request);
        System.out.println("Received response: " + response);
        System.out.println("User with name '" + name + "' added");
    }
}
