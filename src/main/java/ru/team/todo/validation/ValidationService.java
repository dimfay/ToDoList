package ru.team.todo.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ru.team.todo.validation.rules.ValidationRule;

public class ValidationService<T> {

    private final List<ValidationRule<T>> validationRules;

    public ValidationService(List<ValidationRule<T>> validationRules) {
        this.validationRules = validationRules;
    }

    public List<CoreError> validate(T data) {
        List<CoreError> errors = new ArrayList<>();
        if (data == null) {
            errors.add(new CoreError("Data should not be null"));
            return errors;
        }

        return validationRules.stream()
                .map(rule -> mapError(rule, data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private CoreError mapError(ValidationRule<T> rule, T data) {
        try {
            rule.validate(data);
        }
        catch (ValidationException e) {
            return new CoreError(e.getMessage());
        }
        return null;
    }

}
