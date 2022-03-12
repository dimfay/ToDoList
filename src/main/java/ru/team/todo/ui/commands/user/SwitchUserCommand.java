package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.dto.users.SwitchUserRequest;
import ru.team.todo.dto.users.SwitchUserResponse;
import ru.team.todo.injections.DIComponent;
import ru.team.todo.injections.DIDependency;
import ru.team.todo.services.UserService;
import ru.team.todo.ui.commands.Command;

@DIComponent
public class SwitchUserCommand extends Command {

    @DIDependency
    private UserService service;

    public SwitchUserCommand() {
        super("user switch", "Change user");
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter username: ");
        String name = scanner.nextLine();

        SwitchUserRequest request = new SwitchUserRequest(name);
        SwitchUserResponse response = this.service.switchUser(request);
        System.out.println("Received response: " + response);
        if (!response.getErrors().isEmpty()) {
            return;
        }
        System.out.println("You are '" + name + "' now");
    }
}
