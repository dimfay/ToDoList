package ru.team.todo.dto.users;

import java.util.List;

public class FindUserRequest {

    private final List<String> users;

    public FindUserRequest(List<String> users) {
        this.users = users;
    }

    public List<String> getUsers() {
        return this.users;
    }

    @Override
    public String toString() {
        return "FindUserRequest{" +
                "users=" + users +
                '}';
    }
}

