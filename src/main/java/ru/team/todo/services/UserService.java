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
import ru.team.todo.repository.UserRepository;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.CoreError;
import ru.team.todo.validation.ValidationService;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository repository;
    private final ConsoleSession consoleSession;
    private final ValidationService<AddUserRequest> addUserValidationService;
    private final ValidationService<RemoveUserRequest> removeUserValidationService;
    private final ValidationService<SwitchUserRequest> switchUserValidationService;
    private final ValidationService<FindUserRequest> findUserValidationService;

    public UserService(UserRepository repository, ConsoleSession consoleSession,
                       ValidationService<AddUserRequest> addUserValidationService,
                       ValidationService<RemoveUserRequest> removeUserValidationService,
                       ValidationService<SwitchUserRequest> switchUserValidationService,
                       ValidationService<FindUserRequest> findUserValidationService) {

        this.repository = repository;
        this.consoleSession = consoleSession;
        this.addUserValidationService = addUserValidationService;
        this.removeUserValidationService = removeUserValidationService;
        this.switchUserValidationService = switchUserValidationService;
        this.findUserValidationService = findUserValidationService;
    }

    public AddUserResponse addUser(AddUserRequest request) {
        var validationResult = this.addUserValidationService.validate(request);
        if (!validationResult.isEmpty()) {
            return new AddUserResponse(validationResult);
        }

        var user = convertUserRequest(request);

        //TODO Не понятно, это нормально что проверяется наличие пользователя здесь а не в репозитории?
        User tmpUser = this.repository.getUserByName(user.getName());
        if (tmpUser != null) {
            return new AddUserResponse(List.of(new CoreError("User '" + user.getName() + "' already exists!")));
        }

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
