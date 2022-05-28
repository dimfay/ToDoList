package ru.team.todo.dto.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Integer id;
    private String name;
    private String description;


}
