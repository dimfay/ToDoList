package ru.team.todo.validation.requests.task;

import ru.team.todo.dto.tasks.DeleteTaskByIdRequest;
import ru.team.todo.injections.DIComponent;
import ru.team.todo.validation.ValidationService;
import ru.team.todo.validation.rules.ValidationRule;
import ru.team.todo.validation.rules.task.WrongIdRule;

import java.util.List;

@DIComponent
public class DeleteTaskByIdRequestValidation extends ValidationService<DeleteTaskByIdRequest> {

    @Override
    protected List<ValidationRule<DeleteTaskByIdRequest>> getValidationRules() {
        return List.of(new WrongIdRule<>());
    }

}
