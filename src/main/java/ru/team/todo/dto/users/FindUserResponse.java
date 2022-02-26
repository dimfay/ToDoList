package ru.team.todo.dto.users;

import ru.team.todo.domain.User;
import ru.team.todo.validation.CoreError;

import java.util.List;

public class FindUserResponse {

    private final List<CoreError> errors;
    private final List<User> users;

    public FindUserResponse(List<CoreError> errors, List<User> users) {
        this.errors = errors;
        this.users = users;
    }

    public List<CoreError> getErrors() {
        return this.errors;
    }

    public List<User> getUsers() {
        return this.users;
    }

    @Override
    public String toString() {
        return "FindUserResponse{" +
                "errors=" + errors +
                ", users=" + users +
                '}';
    }
}
