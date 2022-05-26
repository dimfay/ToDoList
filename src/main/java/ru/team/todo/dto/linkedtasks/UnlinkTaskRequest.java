package ru.team.todo.dto.linkedtasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnlinkTaskRequest {
    private Integer parentTaskId;
    private Integer childTaskId;

}
