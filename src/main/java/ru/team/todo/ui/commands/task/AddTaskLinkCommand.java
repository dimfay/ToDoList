package ru.team.todo.ui.commands.task;

import ru.team.todo.services.TaskService;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.ui.commands.Command;

public class AddTaskLinkCommand extends Command {

    private final TaskService service;

    public AddTaskLinkCommand(TaskService service) {
        super("task add link", "Add subtasks to the task");
        this.service = service;
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException();
    }
}
