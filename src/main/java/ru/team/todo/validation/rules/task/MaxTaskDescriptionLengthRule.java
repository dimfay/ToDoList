package ru.team.todo.validation.rules.task;

import ru.team.todo.dto.tasks.NameableTaskRequest;
import ru.team.todo.validation.ValidationException;
import ru.team.todo.validation.rules.ValidationRule;

public class MaxTaskDescriptionLengthRule<T extends NameableTaskRequest> implements ValidationRule<T> {

    public void onValidate(T task) {
        if (task.getName().length() > 1000) {
            throw new ValidationException("Task description should be less than 1000 characters.");
        }
    }
}
