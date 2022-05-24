package ru.team.todo.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.team.todo.validation.CoreError;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoveUserResponse {
    private List<CoreError> errors;
}
