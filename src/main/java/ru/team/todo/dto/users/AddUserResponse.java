package ru.team.todo.dto.users;

import java.util.List;

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
    public String toString() {
        return "AddUserResponse{" +
                "errors=" + errors +
                '}';
    }
}
