package ru.team.todo.ui.commands.user;

import ru.team.todo.managers.UserService;
import ru.team.todo.objects.User;
import ru.team.todo.ui.commands.Command;

import java.util.Collection;

public class FindAllUsers extends Command {
	private final UserService manager;

    public FindAllUsers(UserService manager) {
        super("user find", "Find all available users");
        this.manager = manager;
    }

    @Override
    public void execute() {
        Collection<User> users = manager.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Users not found!");
            return;
        }
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

}
