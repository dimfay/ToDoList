package ru.team.todo.ui.commands.task;

import ru.team.todo.services.ConsoleService;
import ru.team.todo.ui.commands.Command;

public class AddTaskLinkCommand extends Command {

    private final ConsoleService consoleService;

    public AddTaskLinkCommand(ConsoleService consoleService) {
        super("task add link", "Add subtasks to the task");
        this.consoleService = consoleService;
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException();
    }
}
