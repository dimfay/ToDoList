package ru.team.todo.validation.rules;

import ru.team.todo.validation.ValidationException;

public interface ValidationRule<T> {

    void onValidate(T data);

    default void validate(T data) {
        if (data == null) {
            throw new ValidationException("Data must not be null.");
        }

        onValidate(data);
    }
}
