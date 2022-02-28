package ru.team.todo.validation.rules.users;

import ru.team.todo.dto.users.FindUserRequest;
import ru.team.todo.validation.ValidationException;
import ru.team.todo.validation.rules.ValidationRule;

public class FindUserNameLengthRule implements ValidationRule<FindUserRequest> {

    @Override
    public void onValidate(FindUserRequest data) {
        for (String names : data.getUsers()) {
            if (names.length() > 15) {
                throw new ValidationException("Username should be at most 15 characters.");
            }
        }
    }
}
