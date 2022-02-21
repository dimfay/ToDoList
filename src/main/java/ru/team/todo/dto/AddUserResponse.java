package ru.team.todo.dto;

import java.util.List;
import java.util.Objects;

import ru.team.todo.validation.CoreError;

public class AddUserResponse {
    private List<CoreError> errors;

    public void setErrors(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AddUserResponse other = (AddUserResponse) obj;
        return Objects.equals(errors, other.errors);
    }

    @Override
    public String toString() {
        return "addUserResponse [errors=" + errors + "]";
    }


}
