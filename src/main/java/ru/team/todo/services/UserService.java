package ru.team.todo.services;

import ru.team.todo.dto.AddUserRequest;
import ru.team.todo.dto.AddUserResponse;
import ru.team.todo.objects.User;
import ru.team.todo.repository.UserRepository;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.ValidationService;

public class UserService {

    private final UserRepository repository;
    private final ConsoleSession consoleSession;
    private final ValidationService validationService;

    public UserService(UserRepository repository, ConsoleSession consoleSession, ValidationService validationService) {
        this.repository = repository;
        this.consoleSession = consoleSession;
        this.validationService = validationService;
    }

    //TODO Добавить request and response
    public AddUserResponse addUser(AddUserRequest request) {
        var user = convert(request);
        var validationResult = validationService.validate(user);
        if (!validationResult.isEmpty()) {
            System.out.println("Validation failed, errors: " + validationResult);
            var response = new AddUserResponse();
            response.setErrors(validationResult);
            return response;
        }

        var createdUser = repository.addUser(user);
        System.out.println("Successfully saved: " + createdUser);

        //TODO implement proper response
        var response = new AddUserResponse();
        /*
         *
         * response.setCreatedToDoId(createdEntity.getId());
         * System.out.println("Sending response: " + response);
         */
        return response;

    }

    private User convert(AddUserRequest addUserRequest) {
        return new User(addUserRequest.name);
    }

    //TODO Добавить валидацию и response
    public void removeUser(String name) {
        this.repository.removeUser(name);
    }

    //TODO Добавить валидацию и response
    public void switchUser(String name) {
        User user = this.repository.getUserByName(name);
        if (user == null) {
            return;
        }
        this.consoleSession.setSwitchedUser(user);
    }

}
