package ru.team.todo.validation.rules;

import ru.team.todo.objects.Task;
import ru.team.todo.objects.User;
import ru.team.todo.validation.ValidationException;

public interface ValidationRule {
    default void validate(User user) {
        return;
    }

    default void validate(Task task) {
        return;
    }

    default void checkNotNull(Task task) {
        if (task == null) {
            throw new ValidationException("ToDo must not be null.");
        }
    }
}
