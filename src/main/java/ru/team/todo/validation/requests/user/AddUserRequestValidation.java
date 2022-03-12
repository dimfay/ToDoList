package ru.team.todo.validation.requests.user;

import ru.team.todo.dto.users.AddUserRequest;
import ru.team.todo.injections.DIComponent;
import ru.team.todo.validation.ValidationService;
import ru.team.todo.validation.rules.ValidationRule;
import ru.team.todo.validation.rules.users.UserNameLengthRule;

import java.util.List;

@DIComponent
public class AddUserRequestValidation extends ValidationService<AddUserRequest> {

    @Override
    protected List<ValidationRule<AddUserRequest>> getValidationRules() {
        return List.of(new UserNameLengthRule<>());
    }

}
