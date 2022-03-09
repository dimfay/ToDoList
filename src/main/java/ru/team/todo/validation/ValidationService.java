package ru.team.todo.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ru.team.todo.validation.rules.ValidationRule;

public abstract class ValidationService<T> {

    public List<CoreError> validate(T data) {
        List<CoreError> errors = new ArrayList<>();
        if (data == null) {
            errors.add(new CoreError("Data should not be null"));
            return errors;
        }

        return getValidationRules().stream()
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

    protected abstract List<ValidationRule<T>> getValidationRules();
}
