package ru.team.todo.services;

import ru.team.todo.dto.users.AddUserRequest;
import ru.team.todo.dto.users.AddUserResponse;
import ru.team.todo.dto.users.FindUserRequest;
import ru.team.todo.dto.users.FindUserResponse;
import ru.team.todo.dto.users.RemoveUserRequest;
import ru.team.todo.dto.users.RemoveUserResponse;
import ru.team.todo.dto.users.SwitchUserRequest;
import ru.team.todo.dto.users.SwitchUserResponse;
import ru.team.todo.domain.User;
import ru.team.todo.injections.DIComponent;
import ru.team.todo.injections.DIDependency;
import ru.team.todo.repository.UserRepository;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.CoreError;
import ru.team.todo.validation.requests.user.AddUserRequestValidation;
import ru.team.todo.validation.requests.user.FindUserRequestValidation;
import ru.team.todo.validation.requests.user.RemoveUserRequestValidation;
import ru.team.todo.validation.requests.user.SwitchUserRequestValidation;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class UserService {

    @DIDependency
    private UserRepository repository;
    @DIDependency
    private ConsoleSession consoleSession;
    @DIDependency
    private AddUserRequestValidation addUserValidationService;
    @DIDependency
    private RemoveUserRequestValidation removeUserValidationService;
    @DIDependency
    private SwitchUserRequestValidation switchUserValidationService;
    @DIDependency
    private FindUserRequestValidation findUserValidationService;

    public AddUserResponse addUser(AddUserRequest request) {
        var validationResult = this.addUserValidationService.validate(request);
        if (!validationResult.isEmpty()) {
            return new AddUserResponse(validationResult);
        }

        //TODO Не понятно, это нормально что проверяется наличие пользователя здесь а не в репозитории?
        User tmpUser = this.repository.getUserByName(request.getName());
        if (tmpUser != null) {
            return new AddUserResponse(List.of(new CoreError("User '" + request.getName() + "' already exists!")));
        }

        var user = convertUserRequest(request);

        this.repository.addUser(user);
        return new AddUserResponse(List.of());
    }

    public RemoveUserResponse removeUser(RemoveUserRequest request) {
        var validationResult = this.removeUserValidationService.validate(request);
        if (!validationResult.isEmpty()) {
            return new RemoveUserResponse(validationResult);
        }

        //TODO Не понятно, это нормально что проверяется наличие пользователя здесь а не в репозитории?
        User tmpUser = this.repository.getUserByName(request.getName());
        if (tmpUser == null) {
            return new RemoveUserResponse(List.of(new CoreError("User '" + request.getName() + "' not found!")));
        }

        this.repository.removeUser(request.getName());
        return new RemoveUserResponse(List.of());
    }

    public SwitchUserResponse switchUser(SwitchUserRequest request) {
        var validationResult = this.switchUserValidationService.validate(request);
        if (!validationResult.isEmpty()) {
            return new SwitchUserResponse(validationResult);
        }

        User user = this.repository.getUserByName(request.getName());
        if (user == null) {
            return new SwitchUserResponse(List.of(new CoreError("User not selected.")));
        }

        this.consoleSession.setSwitchedUser(user);
        return new SwitchUserResponse(List.of());
    }

    public FindUserResponse findUsers(FindUserRequest request) {
        var validationResult = this.findUserValidationService.validate(request);
        if (!validationResult.isEmpty()) {
            return new FindUserResponse(validationResult, List.of());
        }

        //Если запрос пустой, возвращаем всех пользователей
        if (request.getUsers().isEmpty()) {
            return new FindUserResponse(List.of(), new ArrayList<>(this.repository.getAllUsers()));
        }

        //Извлекаем каждого пользователя из репозитория по запросу
        List<CoreError> findError = new ArrayList<>();
        List<User> findUsers = new ArrayList<>();
        for (String requestedUser : request.getUsers()) {
            User user = this.repository.getUserByName(requestedUser);
            if (user == null) {
                findError.add(new CoreError("User '" + requestedUser + "' not found!"));
                continue;
            }

            findUsers.add(user);
        }

        return new FindUserResponse(findError, findUsers);
    }

    private static User convertUserRequest(AddUserRequest addUserRequest) {
        return new User(addUserRequest.getName());
    }

}
