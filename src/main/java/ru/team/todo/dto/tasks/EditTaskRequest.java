package ru.team.todo.dto.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditTaskRequest {
    @NotNull(message = "Must not be empty")
    private Integer id;
    @NotBlank(message = "Must not be empty")
    @Length(max = 16, message = "Task name must not be more than 16 characters")
    private String name;
    @NotBlank(message = "Must not be empty")
    @Length(max = 1000, message = "Description must not be more than 1000 characters")
    private String description;
}
