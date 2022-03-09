package ru.team.todo.validation.requests.user;

import ru.team.todo.dto.users.RemoveUserRequest;
import ru.team.todo.injections.DIComponent;
import ru.team.todo.validation.ValidationService;
import ru.team.todo.validation.rules.ValidationRule;
import ru.team.todo.validation.rules.users.UserNameLengthRule;

import java.util.List;

@DIComponent
public class RemoveUserRequestValidation extends ValidationService<RemoveUserRequest> {

    @Override
    protected List<ValidationRule<RemoveUserRequest>> getValidationRules() {
        return List.of(new UserNameLengthRule<>());
    }

}