package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.team.todo.dto.users.AddUserRequest;
import ru.team.todo.services.UserService;
import ru.team.todo.ui.commands.Command;

@Component
public class AddUserCommand extends Command {

    @Autowired
    private UserService service;

    public AddUserCommand() {
        super("user add", "Add new user");
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
