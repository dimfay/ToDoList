package ru.team.todo.dto.users;

import java.util.Objects;

public class RemoveUserRequest implements NameableUserRequest {

    private final String name;

    public RemoveUserRequest(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveUserRequest request = (RemoveUserRequest) o;
        return Objects.equals(name, request.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "RemoveUserRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
