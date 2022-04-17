package ru.team.todo.dto.users;


import lombok.Data;
import lombok.NonNull;

@Data
public class RemoveUserRequest implements NameableUserRequest {

    @NonNull
    private final String name;
}
