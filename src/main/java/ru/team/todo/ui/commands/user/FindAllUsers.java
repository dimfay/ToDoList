package ru.team.todo.ui.commands.user;

import java.util.Collections;
import java.util.Scanner;

import ru.team.todo.managers.UserService;
import ru.team.todo.ui.commands.Command;

public class FindAllUsers extends Command {
	private final UserService manager;

    public FindAllUsers(UserService manager) {
        super("find", "Find all available users");
        this.manager = manager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        manager.findAllUsers();
    }

}
