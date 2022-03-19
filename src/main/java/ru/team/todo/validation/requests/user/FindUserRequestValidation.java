package ru.team.todo.validation.requests.user;

import org.springframework.stereotype.Component;
import ru.team.todo.dto.users.FindUserRequest;
import ru.team.todo.validation.ValidationService;
import ru.team.todo.validation.rules.ValidationRule;
import ru.team.todo.validation.rules.users.FindUserNameLengthRule;

import java.util.List;

@Component
public class FindUserRequestValidation extends ValidationService<FindUserRequest> {

    @Override
    protected List<ValidationRule<FindUserRequest>> getValidationRules() {
        return List.of(new FindUserNameLengthRule());
    }

}
