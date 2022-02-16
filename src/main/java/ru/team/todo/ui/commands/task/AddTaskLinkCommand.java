package ru.team.todo.ui.commands.task;

import ru.team.todo.managers.UserService;
import ru.team.todo.ui.commands.Command;

public class AddTaskLinkCommand extends Command {
    private final UserService manager;

    public AddTaskLinkCommand(UserService manager) {
        super("task add link", "Add subtasks to the task");
        this.manager = manager;
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException();
    }
}
