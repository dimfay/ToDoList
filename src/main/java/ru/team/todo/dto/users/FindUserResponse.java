package ru.team.todo.dto.users;

import lombok.Data;
import ru.team.todo.domain.User;
import ru.team.todo.validation.CoreError;

import java.util.List;

@Data
public class FindUserResponse {

    private final List<CoreError> errors;
    private final List<User> users;
}
