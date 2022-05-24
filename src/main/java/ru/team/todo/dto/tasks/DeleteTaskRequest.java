package ru.team.todo.dto.tasks;

import java.util.Objects;

public class DeleteTaskRequest implements IdTaskRequest {
    private final int id;

    public DeleteTaskRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteTaskRequest that = (DeleteTaskRequest) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DeleteTaskRequestById{" +
                "id=" + id +
                '}';
    }
}

