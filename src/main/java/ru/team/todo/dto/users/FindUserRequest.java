package ru.team.todo.dto.users;

import lombok.Data;
import java.util.List;

@Data
public class FindUserRequest {

    private final List<String> users;
}

