package ru.team.todo.validation.rules.users;

import ru.team.todo.dto.users.NameableUserRequest;
import ru.team.todo.validation.ValidationException;
import ru.team.todo.validation.rules.ValidationRule;

public class UserNameLengthRule<T extends NameableUserRequest> implements ValidationRule<T> {

    @Override
    public void onValidate(T user) {
        String name = user.getName();
        if (name.length() > 15) {
            throw new ValidationException("Username should be at most 15 characters.");
        }

        if (name.length() < 3) {
            throw new ValidationException("Username should be at least 3 characters");
        }
    }

}
