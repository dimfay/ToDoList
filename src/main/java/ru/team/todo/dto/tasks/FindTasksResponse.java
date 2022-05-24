package ru.team.todo.dto.tasks;

import ru.team.todo.validation.CoreError;

import java.util.List;
import java.util.Objects;

public class FindTasksResponse {
    private List<CoreError> errors;
    private List<TaskDTO> tasks;

    public FindTasksResponse() {

    }

    public FindTasksResponse(List<CoreError> errors, List<TaskDTO> tasks){
        this.errors = errors;
        this.tasks = tasks;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setErrors(List<CoreError> errors) {
        this.errors = errors;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
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
