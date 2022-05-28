package ru.team.todo.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequest {
    @NotBlank(message = "Must not be empty")
    @Length(min = 3, max = 15, message = "Username must not be less than 3 or more than 15 characters")
    private String name;
}
