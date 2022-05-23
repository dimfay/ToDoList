package ru.team.todo.validation.requests.task;

import org.springframework.stereotype.Component;
import ru.team.todo.dto.tasks.DeleteTaskRequest;
import ru.team.todo.validation.ValidationService;
import ru.team.todo.validation.rules.ValidationRule;
import ru.team.todo.validation.rules.task.WrongIdRule;

import java.util.List;

@Component
public class DeleteTaskRequestValidation extends ValidationService<DeleteTaskRequest> {

    @Override
    protected List<ValidationRule<DeleteTaskRequest>> getValidationRules() {
        return List.of(new WrongIdRule<>());
    }

}
