package ru.team.todo.dto.users;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.team.todo.dto.CoreError;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserResponse {
    private List<CoreError> errors;
    private UserDTO user;
}
