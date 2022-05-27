package ru.team.todo.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindUserRequest implements NameableUserRequest {
    @NotBlank(message = "Must not be empty")
    private String name;
}

