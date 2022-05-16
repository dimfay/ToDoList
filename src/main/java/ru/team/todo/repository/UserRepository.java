package ru.team.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.team.todo.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByName(String name);

}
