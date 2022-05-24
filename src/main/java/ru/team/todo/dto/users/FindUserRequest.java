package ru.team.todo.dto.users;

import java.util.Objects;

public class FindUserRequest {
    private String name;

    public FindUserRequest() {

    }

    public FindUserRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindUserRequest request = (FindUserRequest) o;
        return Objects.equals(name, request.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "FindUserRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}

