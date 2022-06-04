package ru.team.todo.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team.todo.dto.users.*;
import ru.team.todo.domain.User;
import ru.team.todo.repository.UserRepository;
import ru.team.todo.dto.CoreError;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public AddUserResponse addUser(AddUserRequest request) {
        User tmpUser = this.repository.findByName(request.getName());
        if (tmpUser != null) {
            return new AddUserResponse(List.of(new CoreError("User '" + request.getName() + "' already exists!")), null);
        }

        User user = this.repository.save(convert(request));
        return new AddUserResponse(List.of(), convert(user));
    }

    public DeleteUserResponse deleteUser(DeleteUserRequest request) {
        User tmpUser = this.repository.findByName(request.getName());
        if (tmpUser == null) {
            return new DeleteUserResponse(List.of(new CoreError("User '" + request.getName() + "' not found!")));
        }

        this.repository.delete(tmpUser);
        return new DeleteUserResponse(List.of());
    }

    public FindUserResponse findUser(FindUserRequest request) {
        String requestedUser = request.getName();
        if (requestedUser != null) {
            User tmpUser = this.repository.findByName(requestedUser);
            if (tmpUser == null) {
                return new FindUserResponse(List.of(new CoreError("User '" + requestedUser + "' not found!")), null);
            }

            return new FindUserResponse(List.of(), List.of(convert(tmpUser)));
        }

        List<UserDTO> dto = new ArrayList<>();
        for (User user : this.repository.findAll()) {
            dto.add(convert(user));
        }
        return new FindUserResponse(List.of(), dto);
    }

    private UserDTO convert(User user) {
        return new UserDTO(user.getId(), user.getName());
    }

    private User convert(AddUserRequest request) {
        return new User(request.getName());
    }

}
