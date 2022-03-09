package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.dto.users.RemoveUserRequest;
import ru.team.todo.dto.users.RemoveUserResponse;
import ru.team.todo.injections.DIComponent;
import ru.team.todo.injections.DIDependency;
import ru.team.todo.services.UserService;
import ru.team.todo.ui.commands.Command;

@DIComponent
public class DeleteUserCommand extends Command {

    @DIDependency
    private UserService service;

    public DeleteUserCommand() {
        super("user delete", "Delete the user");
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
