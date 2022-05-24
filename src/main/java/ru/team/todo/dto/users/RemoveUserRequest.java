package ru.team.todo.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoveUserRequest implements NameableUserRequest {
    private String name;
}
