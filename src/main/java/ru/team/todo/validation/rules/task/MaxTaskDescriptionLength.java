package ru.team.todo.validation.rules.task;

import ru.team.todo.domain.Task;
import ru.team.todo.validation.ValidationException;
import ru.team.todo.validation.rules.ValidationRule;

public class MaxTaskDescriptionLength implements ValidationRule<Task> {

    public void onValidate(Task task) {
        if (task.getName().length() > 1000) {
            throw new ValidationException("Task description should be less than 1000 characters.");
        }
    }
}
