package ru.team.todo.dto.tasks;

import java.util.Objects;

public class DeleteTaskByNameRequest implements NameableTaskRequest {
    private final String name;

    public DeleteTaskByNameRequest(String name){
        this.name = name;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getDescription(){
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteTaskByNameRequest that = (DeleteTaskByNameRequest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "DeleteTaskByNameRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
