package ru.team.todo.validation;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
