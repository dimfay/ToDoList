package ru.team.todo.dto.users;

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
    public String toString() {
        return "RemoveUserRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
