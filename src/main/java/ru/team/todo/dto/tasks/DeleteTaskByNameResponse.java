package ru.team.todo.dto.tasks;

import ru.team.todo.validation.CoreError;

import java.util.List;
import java.util.Objects;

public class DeleteTaskByNameResponse {
    private final List<CoreError> errors;

    public DeleteTaskByNameResponse(List<CoreError> errors){
        this.errors = errors;
    }

    public List<CoreError> getErrors(){
        return errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteTaskByNameResponse that = (DeleteTaskByNameResponse) o;
        return Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors);
    }

    @Override
    public String toString() {
        return "DeleteTaskByNameResponse{" +
                "errors=" + errors +
                '}';
    }
}
