package ru.team.todo.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.team.todo.domain.User;
import ru.team.todo.repository.UserRepository;

@Component
public class ConsoleSession {

    @Autowired
    private UserRepository repository;

    private String switchedUser;

    /**
     * Пытается каждый раз извлечь из базы данных текущего пользователя. Полезно тем, что извлекая пользователя,
     * он извлекает еще и все таски этого пользователя, магическим образом через Hibernate.
     */
    public User getSwitchedUser() {
        return this.repository.getUserByName(this.switchedUser);
    }

    public void setSwitchedUser(String switchedUser) {
        this.switchedUser = switchedUser;
    }

}
