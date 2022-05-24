package ru.team.todo.dto.users;

import java.util.List;
import java.util.Objects;

import ru.team.todo.validation.CoreError;

public class AddUserResponse {
    private List<CoreError> errors;
    private UserDTO user;

    public AddUserResponse() {

    }

    public AddUserResponse(List<CoreError> errors, UserDTO user) {
        this.errors = errors;
        this.user = user;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    public void setErrors(List<CoreError> errors) {
        this.errors = errors;
    }

    public UserDTO getUser() {
        return this.user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddUserResponse response = (AddUserResponse) o;
        return Objects.equals(errors, response.errors) && Objects.equals(user, response.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors, user);
    }

    @Override
    public String toString() {
        return "AddUserResponse{" +
                "errors=" + errors +
                ", user=" + user +
                '}';
    }
}
