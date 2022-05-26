package ru.team.todo.dto.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTaskRequest implements NameableTaskRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String taskName;
    @NotBlank
    private String taskDescription;
}
