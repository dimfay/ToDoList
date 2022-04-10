package ru.team.todo.dto.tasks;

import ru.team.todo.domain.Task;
import ru.team.todo.validation.CoreError;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class FindTasksResponse {
    private final List<CoreError> errors;
    private final Collection<Task> tasks;

    public FindTasksResponse(List<CoreError> errors, Collection<Task> tasks){
        this.errors = errors;
        this.tasks = tasks;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindTasksResponse that = (FindTasksResponse) o;
        return Objects.equals(errors, that.errors) && Objects.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors, tasks);
    }

    @Override
    public String toString() {
        return "FindTasksResponse{" +
                "errors=" + errors +
                ", tasks=" + tasks +
                '}';
    }
}
