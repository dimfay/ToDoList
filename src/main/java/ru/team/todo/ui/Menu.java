package ru.team.todo.ui;

import ru.team.todo.managers.TaskService;
import ru.team.todo.managers.UserService;
import ru.team.todo.ui.commands.Command;

import java.util.*;

public class Menu {

	private final Map<String, Command> taskCommands = new HashMap<>();
	private final Map<String, Command> userCommands = new HashMap<>();
	
	private UserService userService;
	private TaskService taskService;

	public Menu(TaskService taskService) {
		this.taskService = taskService;
		this.userService = taskService.getUserService();
	}

	public Menu addTaskCommand(Command command) {
		taskCommands.put(command.getName(), command);
		return this;
	}
	
	public Menu addUserCommand(Command command) {
		userCommands.put(command.getName(), command);
		return this;
	}

	public void startUI() {
		while (true) {	
			try { 
				System.out.println("Available commands: ");
				System.out.println("1. User commands");
				System.out.println("2. Task commands");
				Scanner scanner = new Scanner(System.in);
				int category = Integer.parseInt(scanner.nextLine());
				
				if (category == 1) {
					printUserCommands();
					String userInput = scanner.nextLine();

					userInput = userInput.trim();
					if (userInput.isEmpty()) {
						continue;
					}
					Command cmd = userCommands.get(userInput.toLowerCase());
					if (cmd == null) {
						//throw new IllegalArgumentException("Command '" + userInput + "' not found!");
						System.out.println("Command not found!");
						continue;
					}
					
					cmd.execute();
				} else if (category == 2){ 
					if (userService.getAllUsers().isEmpty()) {
						System.out.println("There are no users, you should add one.");
						continue;
					} else if (userService.getCurrentUser().isEmpty()) {
						System.out.println("You need you need to switch user");
						continue;
					}
					System.out.println("You are: " + userService.getCurrentUser().get().getName());
					taskService.setUser();
					printTaskCommands();
					
					String userInput = scanner.nextLine();

					userInput = userInput.trim();
					if (userInput.isEmpty()) {
						continue;
					}
					Command cmd = taskCommands.get(userInput.toLowerCase());
					
					if (cmd == null) {
						//throw new IllegalArgumentException("Command '" + userInput + "' not found!");
						System.out.println("Command not found!");
						continue;
					}
					
					cmd.execute();
				}
				
			  
			} catch (Exception e) { 
				e.printStackTrace(); 
			}
			 
		}
	}

	private void printUserCommands() {
		List<Command> sortedCommands = new ArrayList<>(userCommands.values());
		Collections.sort(sortedCommands);
		System.out.println("\tUser commands:");
		for (Command cmd : sortedCommands) {
			System.out.println("'" + cmd.getName() + "': " + cmd.getDescription());
		}
	}

	private void printTaskCommands() {
		List<Command> sortedCommands = new ArrayList<>(taskCommands.values());
		Collections.sort(sortedCommands);
		System.out.println("\tTask commands:");
		for (Command cmd : sortedCommands) {
			System.out.println("'" + cmd.getName() + "': " + cmd.getDescription());
		}
	}

}
