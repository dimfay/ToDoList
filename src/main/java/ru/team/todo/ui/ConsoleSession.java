package ru.team.todo.ui;

import org.springframework.stereotype.Component;
import ru.team.todo.domain.User;

@Component
public class ConsoleSession {

    private User switchedUser = null;

    public User getSwitchedUser() {
        return this.switchedUser;
    }

    public void setSwitchedUser(User switchedUser) {
        this.switchedUser = switchedUser;
    }

}
