package ru.team.todo.dto.tasks;

import lombok.Data;
import ru.team.todo.domain.Task;

import java.util.Collection;

@Data
public class FindTasksRequest {

    private final Collection<Task> tasks;
}
