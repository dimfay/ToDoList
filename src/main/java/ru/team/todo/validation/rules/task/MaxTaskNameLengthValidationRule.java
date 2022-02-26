package ru.team.todo.validation.rules.task;

import ru.team.todo.domain.Task;
import ru.team.todo.validation.ValidationException;
import ru.team.todo.validation.rules.ValidationRule;

public class MaxTaskNameLengthValidationRule implements ValidationRule<Task> {
    public void onValidate(Task task) {
        if (task.getName().length() > 15) {
            throw new ValidationException("Task name should not exceed 15 characters.");
        }
    }
}
