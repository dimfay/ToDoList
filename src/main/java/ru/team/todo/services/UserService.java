package ru.team.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.team.todo.dto.users.*;
import ru.team.todo.domain.User;
import ru.team.todo.repository.UserRepository;
import ru.team.todo.validation.CoreError;
import ru.team.todo.validation.requests.user.RemoveUserRequestValidation;
import ru.team.todo.validation.requests.user.SwitchUserRequestValidation;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private RemoveUserRequestValidation removeUserValidationService;
    @Autowired
    private SwitchUserRequestValidation switchUserValidationService;

    public AddUserResponse addUser(AddUserRequest request) {
        var user = convert(request);
        this.repository.save(user);
        var response = new AddUserResponse();
        response.setCreatedUserId(user.getId());
        return response;
    }

    private User convert(AddUserRequest request){
        var user = new User();
        user.setName(request.getName());
        return user;
    }

    public RemoveUserResponse removeUser(RemoveUserRequest request) {
        var validationResult = this.removeUserValidationService.validate(request);
        if (!validationResult.isEmpty()) {
            return new RemoveUserResponse(validationResult);
        }

        User tmpUser = this.repository.findByName(request.getName());
        if (tmpUser == null) {
            return new RemoveUserResponse(List.of(new CoreError("User '" + request.getName() + "' not found!")));
        }

        this.repository.delete(tmpUser);
        return new RemoveUserResponse(List.of());
    }

    public List<UserDTO> findAllUsersBy(FindUserRequest request) {
        var specifications = getFindSpecifications(request);
        var entities = specifications.stream()
                .reduce(Specification::and)
                .map(e -> repository.findAll())
                .orElseGet(repository::findAll);
        return entities.stream()
                .map(this::convert)
                .toList();
    }

    public UserDTO findUserByName(String name){
        return  convert(repository.findByName(name));

    }

    private List<Specification<User>> getFindSpecifications(FindUserRequest request){
        var specifications = new ArrayList<Specification<User>>();
        var name = request.getName();
        if (name != null){
            Specification<User> specification = ((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("name"), name));
            specifications.add(specification);
        }
        return specifications;
    }

    private UserDTO convert(User user){
        return new UserDTO(user.getId(), user.getName());
    }

}
