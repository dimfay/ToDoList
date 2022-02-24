package ru.team.todo.validation.rules;

import ru.team.todo.objects.User;
import ru.team.todo.validation.ValidationException;

public class MaxUserNameLengthValidationRule implements ValidationRule<User> {
    public void onValidate(User user) {
        String name = user.getName();
        if (name.length() > 15) {
            throw new ValidationException("Username should be at most 15 charaters.");
        }
    }

}
