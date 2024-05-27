package com.gl.todo_app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;
    private String title;
    private String description;
    private Boolean completed;
    private LocalDate date;
    private LocalDate dueDate;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = Project.class)
    @JoinColumn(name = "project_id", referencedColumnName = "project_id", columnDefinition = "bigint default 0")
    private Project project;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = Section.class)
    @JoinColumn(name = "section_id", referencedColumnName = "section_id", columnDefinition = "bigint default 0")
    private Section section;
}
