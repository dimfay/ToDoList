package ru.team.todo.validation.rules;

import ru.team.todo.objects.User;
import ru.team.todo.validation.ValidationException;

public class MinUserNameLengthValidationRule implements ValidationRule {
    public void validate(User user) {
        String name = user.getName();
        if (name.length() < 3) {
            throw new ValidationException("Username should be at least 3 characters");
        }
    }
}
