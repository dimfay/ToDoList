package ru.team.todo.dto.users;

public class SwitchUserRequest implements NameableUserRequest {

    private final String name;

    public SwitchUserRequest(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "SwitchUserRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
