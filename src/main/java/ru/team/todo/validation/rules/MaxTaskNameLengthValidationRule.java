package ru.team.todo.validation.rules;

import ru.team.todo.objects.Task;
import ru.team.todo.validation.ValidationException;

public class MaxTaskNameLengthValidationRule implements ValidationRule<Task> {
    public void onValidate(Task task) {
        if (task.getName().length() > 15) {
            throw new ValidationException("Task name should not exceed 15 characters.");
        }
    }
}
