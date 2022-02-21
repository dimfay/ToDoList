package ru.team.todo.ui.commands.user;

import ru.team.todo.objects.User;
import ru.team.todo.services.UserService;
import ru.team.todo.ui.commands.Command;

import java.util.Collection;

public class FindAllUsers extends Command {

    private final UserService service;

    public FindAllUsers(UserService service) {
        super("user find", "Find all available users");
        this.service = service;
    }

    @Override
    public void execute() {
        Collection<User> users = this.service.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Users not found!");
            return;
        }

        for (User user : users) {
            System.out.println(user.toString());
        }
    }

}
