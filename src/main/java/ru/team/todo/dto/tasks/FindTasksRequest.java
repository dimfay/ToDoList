package ru.team.todo.dto.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindTasksRequest {
    @NotBlank(message = "Must not be empty")
    private String userName;
    //Не зачем нам пока искать конкретные таски у пользователя
    //private String task;
}
