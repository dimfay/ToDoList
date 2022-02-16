package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.managers.UserService;
import ru.team.todo.objects.User;
import ru.team.todo.ui.commands.Command;

public class SwitchUserCommand extends Command {
    private final UserService manager;

    public SwitchUserCommand(UserService manager) {
        super("user switch", "Change user");
        this.manager = manager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter username: ");
        String name = scanner.nextLine();

        User user = manager.getUserByName(name);
        if (user == null) {
            System.out.println("User '" + name + "' not found!");
            return;
        }

        manager.setCurrentUser(user);
        System.out.println("You are '" + name + "' now");
    }
}
