package ru.team.todo.dto.users;

import java.util.List;
import java.util.Objects;

import ru.team.todo.validation.CoreError;

public class AddUserResponse {

    private final List<CoreError> errors;

    public AddUserResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddUserResponse response = (AddUserResponse) o;
        return Objects.equals(errors, response.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors);
    }

    @Override
    public String toString() {
        return "AddUserResponse{" +
                "errors=" + errors +
                '}';
    }
}
