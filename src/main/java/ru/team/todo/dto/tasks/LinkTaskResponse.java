package ru.team.todo.dto.tasks;

import lombok.Data;
import ru.team.todo.validation.CoreError;

import java.util.List;

@Data
public class LinkTaskResponse {

    private final List<CoreError> errors;
}
