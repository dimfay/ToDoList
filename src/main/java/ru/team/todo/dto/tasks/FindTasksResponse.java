package ru.team.todo.dto.tasks;

import lombok.Data;
import ru.team.todo.domain.Task;
import ru.team.todo.validation.CoreError;

import java.util.List;

@Data
public class FindTasksResponse {

    private final List<CoreError> errors;
    private final List<Task> tasks;
}
