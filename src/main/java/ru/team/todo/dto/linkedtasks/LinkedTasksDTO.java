package ru.team.todo.dto.linkedtasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.team.todo.domain.LinkedTask;
import ru.team.todo.dto.tasks.TaskDTO;

import java.util.List;

@Data
@AllArgsConstructor
public class LinkedTasksDTO {
    private Integer id;
    private TaskDTO task;
    private TaskDTO linkedTask;
}
