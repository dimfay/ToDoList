package ru.team.todo.dto.users;

import ru.team.todo.validation.CoreError;

import java.util.List;

public class SwitchUserResponse {

    private final List<CoreError> errors;

    public SwitchUserResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "SwitchUserResponse{" +
                "errors=" + errors +
                '}';
    }
}
