package ru.team.todo.ui;

import ru.team.todo.objects.User;

public class ConsoleSession {

    private User switchedUser = null;

    public User getSwitchedUser() {
        return this.switchedUser;
    }

    public void setSwitchedUser(User switchedUser) {
        this.switchedUser = switchedUser;
    }


}