package ru.team.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.team.todo.domain.Task;
import ru.team.todo.domain.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findByName(String name);







//    @Query("SELECT td FROM Task td LEFT JOIN User u ON td.userId = u.id" +
//        " WHERE u.name = :username")
    @Query(value = "SELECT * from tasks t INNER JOIN users u ON t.userId = u.id" +
        " WHERE u.name = :username", nativeQuery = true)
    List<Task> findAllByUserName(@Param("username") String username);
}
