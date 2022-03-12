package ru.team.todo.validation.requests.task;

import ru.team.todo.dto.tasks.AddTaskRequest;
import ru.team.todo.injections.DIComponent;
import ru.team.todo.validation.ValidationService;
import ru.team.todo.validation.rules.ValidationRule;
import ru.team.todo.validation.rules.task.MaxTaskDescriptionLengthRule;
import ru.team.todo.validation.rules.task.MaxTaskNameLengthRule;

import java.util.List;

@DIComponent
public class AddTaskRequestValidation extends ValidationService<AddTaskRequest> {

    @Override
    protected List<ValidationRule<AddTaskRequest>> getValidationRules() {
        return List.of(new MaxTaskNameLengthRule<>(),
                new MaxTaskDescriptionLengthRule<>());
    }

}
