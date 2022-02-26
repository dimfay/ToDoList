package ru.team.todo.ui.commands.user;

import ru.team.todo.domain.User;
import ru.team.todo.dto.users.FindUserRequest;
import ru.team.todo.dto.users.FindUserResponse;
import ru.team.todo.services.UserService;
import ru.team.todo.ui.commands.Command;

import java.util.List;

public class FindAllUsers extends Command {

    private final UserService service;

    public FindAllUsers(UserService service) {
        super("user find", "Find all available users");
        this.service = service;
    }

    @Override
    public void execute() {
        FindUserRequest request = new FindUserRequest(List.of());
        FindUserResponse response = this.service.findUsers(request);
        System.out.println("Received response: " + response);
    }

}
