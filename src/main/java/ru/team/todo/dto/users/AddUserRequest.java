package ru.team.todo.dto.users;

public class AddUserRequest implements NameableUserRequest {

    private final String name;

    public AddUserRequest(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "AddUserRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
