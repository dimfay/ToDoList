package ru.team.todo.validation;

import java.util.Objects;

public class CoreError {
    private final String message;

    public CoreError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoreError error = (CoreError) o;
        return Objects.equals(message, error.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "ToDoError{" +
                "message='" + message + '\'' +
                '}';
    }
}
