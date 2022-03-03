package ru.team.todo.validation.rules.task;

import ru.team.todo.dto.tasks.IdTaskRequest;
import ru.team.todo.validation.ValidationException;
import ru.team.todo.validation.rules.ValidationRule;

public class WrongIdRule<T extends IdTaskRequest> implements ValidationRule<T> {

    @Override
    public void onValidate(T data){
        if (data.getId() < 0)
            throw new ValidationException("Id should be positive.");
    }
}
