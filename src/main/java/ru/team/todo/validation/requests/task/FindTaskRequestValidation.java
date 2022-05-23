package ru.team.todo.validation.requests.task;

import org.springframework.stereotype.Component;
import ru.team.todo.dto.tasks.FindTasksRequest;
import ru.team.todo.validation.ValidationService;
import ru.team.todo.validation.rules.ValidationRule;

import java.util.List;

@Component
public class FindTaskRequestValidation extends ValidationService<FindTasksRequest> {

    //TODO Доделать
    @Override
    protected List<ValidationRule<FindTasksRequest>> getValidationRules() {
        return List.of();
    }

}
