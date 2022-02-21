package ru.team.todo.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ru.team.todo.objects.User;
import ru.team.todo.validation.rules.ValidationRule;

public class ValidationService {

    private final List<ValidationRule> validationRules;

    public ValidationService(List<ValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public List<CoreError> validate(User user) {
        List<CoreError> errors = new ArrayList<>();
        if (user == null) {
            errors.add(new CoreError("User should not be null"));
            return errors;
        }

        return validationRules.stream()
                .map(rule -> mapError(rule, user))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

    }

    private CoreError mapError(ValidationRule rule, User user) {
        try {
            rule.validate(user);
        }
        catch (ValidationException e) {
            return new CoreError(e.getMessage());
        }
        return null;
    }

}
