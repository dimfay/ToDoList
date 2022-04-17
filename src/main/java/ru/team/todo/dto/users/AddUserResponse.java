package ru.team.todo.dto.users;

import java.util.List;
import lombok.Data;
import ru.team.todo.validation.CoreError;

@Data
public class AddUserResponse {

    private final List<CoreError> errors;
}
