package ru.team.todo.dto.users;

import lombok.Data;
import ru.team.todo.validation.CoreError;

import java.util.List;

@Data
public class SwitchUserResponse {

    private final List<CoreError> errors;

}
