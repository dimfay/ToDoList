package ru.team.todo.dto.tasks;

import ru.team.todo.domain.Task;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class FindTasksRequest {
    private final Collection<Task> tasks;

    public FindTasksRequest(List<Task> tasks){
        this.tasks = tasks;
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindTasksRequest that = (FindTasksRequest) o;
        return Objects.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasks);
    }

    @Override
    public String toString() {
        return "FindTasksRequest{" +
                "tasks=" + tasks +
                '}';
    }
}
