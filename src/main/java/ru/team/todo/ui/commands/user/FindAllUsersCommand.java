package ru.team.todo.ui.commands.user;

import ru.team.todo.dto.users.FindUserRequest;
import ru.team.todo.dto.users.FindUserResponse;
import ru.team.todo.injections.DIComponent;
import ru.team.todo.injections.DIDependency;
import ru.team.todo.services.UserService;
import ru.team.todo.ui.commands.Command;

import java.util.List;

@DIComponent
public class FindAllUsersCommand extends Command {

    @DIDependency
    private UserService service;

    public FindAllUsersCommand() {
        super("user find", "Find all available users");
    }

    @Override
    public void execute() {
        FindUserRequest request = new FindUserRequest(List.of());
        FindUserResponse response = this.service.findUsers(request);
        System.out.println("Received response: " + response);
    }

}
