package ru.team.todo.dto.users;

import ru.team.todo.validation.CoreError;

import java.util.List;
import java.util.Objects;

public class RemoveUserResponse {

    private final List<CoreError> errors;

    public RemoveUserResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveUserResponse response = (RemoveUserResponse) o;
        return Objects.equals(errors, response.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors);
    }

    @Override
    public String toString() {
        return "RemoveUserResponse{" +
                "errors=" + errors +
                '}';
    }
}
