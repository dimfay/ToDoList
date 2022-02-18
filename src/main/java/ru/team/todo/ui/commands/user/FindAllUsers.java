package ru.team.todo.ui.commands.user;

import ru.team.todo.repository.UserRepository;
import ru.team.todo.objects.User;
import ru.team.todo.ui.commands.Command;

import java.util.Collection;

public class FindAllUsers extends Command {

    //TODO Скорее всего использовать здесь сервис
    private final UserRepository manager;

    public FindAllUsers(UserRepository manager) {
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
