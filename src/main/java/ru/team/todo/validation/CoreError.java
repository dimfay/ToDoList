package ru.team.todo.validation;

public class CoreError {
    private final String message;

    public CoreError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ToDoError{" +
                "message='" + message + '\'' +
                '}';
    }
}
