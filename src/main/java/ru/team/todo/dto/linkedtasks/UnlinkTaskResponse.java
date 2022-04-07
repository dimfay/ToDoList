package ru.team.todo.dto.linkedtasks;

import ru.team.todo.validation.CoreError;

import java.util.List;
import java.util.Objects;

public class UnlinkTaskResponse {
    private final List<CoreError> errors;

    public UnlinkTaskResponse(List<CoreError> errors){
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnlinkTaskResponse that = (UnlinkTaskResponse) o;
        return Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors);
    }

    @Override
    public String toString() {
        return "UnlinkTaskResponse{" +
                "errors=" + errors +
                '}';
    }
}

