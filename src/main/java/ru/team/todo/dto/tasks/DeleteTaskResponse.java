package ru.team.todo.dto.tasks;

import ru.team.todo.validation.CoreError;

import java.util.List;
import java.util.Objects;

public class DeleteTaskResponse {
    private final List<CoreError> errors;

    public DeleteTaskResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteTaskResponse that = (DeleteTaskResponse) o;
        return Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors);
    }

    @Override
    public String toString() {
        return "DeleteTaksResponseById{" +
                "errors=" + errors +
                '}';
    }
}
