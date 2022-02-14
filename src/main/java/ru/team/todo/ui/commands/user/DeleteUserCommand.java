package ru.team.todo.ui.commands.user;

import java.util.Scanner;

import ru.team.todo.managers.UserService;
import ru.team.todo.ui.commands.Command;

public class DeleteUserCommand extends Command{
	private UserService manager;
	
	public DeleteUserCommand(UserService manager) {
        super("delete", "Delete the user");
        this.manager = manager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter username: ");
        String name = scanner.nextLine();

        manager.deleteUser(name);
        System.out.println("User '" + name + "' deleted");
    }

}
