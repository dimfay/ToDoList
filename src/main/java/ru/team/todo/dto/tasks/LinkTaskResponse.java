package ru.team.todo.dto.tasks;

import ru.team.todo.validation.CoreError;

import java.util.List;
import java.util.Objects;

public class LinkTaskResponse {
    private final List<CoreError> errors;

    public LinkTaskResponse(List<CoreError> errors){
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkTaskResponse that = (LinkTaskResponse) o;
        return Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors);
    }

    @Override
    public String toString() {
        return "LinkTaskResponse{" +
                "errors=" + errors +
                '}';
    }
}
