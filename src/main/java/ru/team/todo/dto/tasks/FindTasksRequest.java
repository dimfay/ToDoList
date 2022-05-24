package ru.team.todo.dto.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindTasksRequest {
    private String userName;
    //Не зачем нам пока искать конкретные таски у пользователя
    //private String task;
}
