package ru.team.todo.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoreError {
    private String message;
}
