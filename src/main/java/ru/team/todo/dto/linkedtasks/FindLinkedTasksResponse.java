package ru.team.todo.dto.linkedtasks;


import lombok.AllArgsConstructor;
import lombok.Data;
import ru.team.todo.validation.CoreError;

import java.util.List;

@Data
@AllArgsConstructor
public class FindLinkedTasksResponse {
    private List<CoreError> errors;
    private List<LinkedTasksDTO> linkedTasks;
}
