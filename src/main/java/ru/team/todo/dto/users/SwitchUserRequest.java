package ru.team.todo.dto.users;

import java.util.Objects;

public class SwitchUserRequest implements NameableUserRequest {

    private String name;

    public SwitchUserRequest(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SwitchUserRequest request = (SwitchUserRequest) o;
        return Objects.equals(name, request.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "SwitchUserRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
