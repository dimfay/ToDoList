package ru.team.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;
    @NaturalId
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    private Set<LinkedTask> linkedTasks;

    public Task() {

    }

    public Task(Integer id, User user, String name, String desc) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = desc;
    }

    public Task(User user, String name, String desc) {
        this.user = user;
        this.name = name;
        this.description = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(name, task.name) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", linkedTasks=" + linkedTasks +
                '}';
    }
}
