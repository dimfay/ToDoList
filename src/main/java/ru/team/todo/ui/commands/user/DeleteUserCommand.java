package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.dto.users.RemoveUserRequest;
import ru.team.todo.dto.users.RemoveUserResponse;
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

        RemoveUserRequest request = new RemoveUserRequest(name);
        RemoveUserResponse response = this.service.removeUser(request);
        System.out.println("Received response: " + response);
        if (!response.getErrors().isEmpty()) {
            return;
        }
        System.out.println("User with name '" + name + "' deleted");
    }

}
