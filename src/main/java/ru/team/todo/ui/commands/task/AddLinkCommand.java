package ru.team.todo.ui.commands.task;

import java.util.Scanner;

import ru.team.todo.managers.TaskService;
import ru.team.todo.ui.commands.Command;

public class AddLinkCommand extends Command {
	private final TaskService manager;
	
	public AddLinkCommand(TaskService manager) {
		super("add link", "Add subtasks to the task");
		this.manager = manager;
	}
	
	@Override
	public void execute() {
		throw new UnsupportedOperationException();
	}
}
