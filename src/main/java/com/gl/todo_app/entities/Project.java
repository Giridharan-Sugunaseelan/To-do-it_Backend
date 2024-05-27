package com.gl.todo_app.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_id;
    private String title;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Task.class)
    private List<Task> tasks = new ArrayList<>();
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Section.class)
    private Set<Section> section = new HashSet<>();
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;
    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", title='" + title + '\'' +
                ", user=" + user +
                '}';
    }
}
