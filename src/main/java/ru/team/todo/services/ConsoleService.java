package ru.team.todo.services;

import ru.team.todo.objects.User;

public class ConsoleService {

    private User switchedUser = null;

    public User getSwitchedUser() {
        return this.switchedUser;
    }

    public void setSwitchedUser(User switchedUser) {
        this.switchedUser = switchedUser;
    }


}
