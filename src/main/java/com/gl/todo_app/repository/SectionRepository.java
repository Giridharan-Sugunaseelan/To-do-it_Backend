package com.gl.todo_app.repository;

import com.gl.todo_app.entities.Project;
import com.gl.todo_app.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Set<Section> getSectionsByProject(Project project);
}
