package com.gl.todo_app.repository;

import com.gl.todo_app.entities.Project;
import com.gl.todo_app.entities.Section;
import com.gl.todo_app.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByProject(Project project);
    List<Task> findAllBySection(Section section);
    List<Task> findByProjectAndDate(Project project, LocalDate date);
    List<Task> findBySectionAndDate(Section section, LocalDate date);
}
