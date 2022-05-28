package ru.team.todo.dto.linkedtasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnlinkTaskRequest {
    @NotNull(message = "Must not be null")
    private Integer parentTaskId;
    @NotNull(message = "Must not be null")
    private Integer childTaskId;
}
