package ru.team.todo.validation.requests.task;

import org.springframework.stereotype.Component;
import ru.team.todo.dto.tasks.DeleteTaskByNameRequest;
import ru.team.todo.validation.ValidationService;
import ru.team.todo.validation.rules.ValidationRule;

import java.util.List;

@Component
public class DeleteTaskByNameRequestValidation extends ValidationService<DeleteTaskByNameRequest> {

    @Override
    protected List<ValidationRule<DeleteTaskByNameRequest>> getValidationRules() {
        return List.of();
    }

}
