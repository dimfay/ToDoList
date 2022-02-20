package ru.team.todo.validation.rules;

import ru.team.todo.objects.Task;
import ru.team.todo.validation.ValidationException;

public class MaxTaskDescriptionLength implements ValidationRule {
	public void validate(Task task) {
		if (task.getName().length() > 1000) {
			throw new ValidationException("Task description should be less than 1000 characters.");
		}
	}
}
