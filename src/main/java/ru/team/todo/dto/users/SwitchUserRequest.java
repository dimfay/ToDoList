package ru.team.todo.dto.users;

import lombok.Data;
import lombok.NonNull;

@Data
public class SwitchUserRequest implements NameableUserRequest {

    @NonNull
    private final String name;

}
