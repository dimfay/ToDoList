package ru.team.todo.dto.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditTaskRequest {
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
