package ru.team.todo.dto.users;

import java.util.List;
import java.util.Objects;

public class FindUserRequest {

    private final List<String> users;

    public FindUserRequest(List<String> users) {
        this.users = users;
    }

    public List<String> getUsers() {
        return this.users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindUserRequest request = (FindUserRequest) o;
        return Objects.equals(users, request.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    @Override
    public String toString() {
        return "FindUserRequest{" +
                "users=" + users +
                '}';
    }
}

