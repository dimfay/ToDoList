package ru.team.todo.dto.users;

import ru.team.todo.validation.CoreError;

import java.util.List;
import java.util.Objects;

public class FindUserResponse {

    private final List<CoreError> errors;
    private final List<UserDTO> users;

    public FindUserResponse(List<CoreError> errors, List<UserDTO> users) {
        this.errors = errors;
        this.users = users;
    }

    public List<CoreError> getErrors() {
        return this.errors;
    }

    public List<UserDTO> getUsers() {
        return this.users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindUserResponse response = (FindUserResponse) o;
        return Objects.equals(errors, response.errors) && Objects.equals(users, response.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors, users);
    }

    @Override
    public String toString() {
        return "FindUserResponse{" +
                "errors=" + errors +
                ", users=" + users +
                '}';
    }
}
