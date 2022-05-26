package ru.team.todo.dto.linkedtasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnlinkTaskRequest {
    @NotBlank
    private String userName;

    @NotBlank
    private String firstTask;
    @NotBlank
    private String secondTask;
}
