package ru.team.todo.dto.users;

import java.util.List;
import java.util.Objects;

import ru.team.todo.validation.CoreError;

public class AddUserResponse {
    private Integer createdUserId;
    private List<CoreError> errors;

    public Integer getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Integer createdUserId) {
        this.createdUserId = createdUserId;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    public void setErrors(List<CoreError> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddUserResponse that = (AddUserResponse) o;
        return createdUserId.equals(that.createdUserId) && errors.equals(that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdUserId, errors);
    }
}
