package ru.team.todo.dto.linkedtasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.team.todo.dto.tasks.TaskDTO;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindLinkedTasksRequest {
    @NotBlank
    private Integer id;
}
