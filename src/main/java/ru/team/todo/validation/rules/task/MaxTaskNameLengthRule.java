package ru.team.todo.validation.rules.task;

import ru.team.todo.dto.tasks.NameableTaskRequest;
import ru.team.todo.validation.ValidationException;
import ru.team.todo.validation.rules.ValidationRule;

public class MaxTaskNameLengthRule<T extends NameableTaskRequest> implements ValidationRule<T> {
    public void onValidate(T task) {
        if (task.getName().length() > 15) {
            throw new ValidationException("Task name should not exceed 15 characters.");
        }
    }
}
