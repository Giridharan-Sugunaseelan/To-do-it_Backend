package com.gl.todo_app.repository;

import com.gl.todo_app.dto.ProjectDto;
import com.gl.todo_app.entities.Project;
import com.gl.todo_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("select p from Project p join p.user u where u.email = :email and p.project_id = :projectId")
    Project getProjectsByEmail(String email, Long projectId);

    @Query("select new com.gl.todo_app.dto.ProjectDto(p.project_id, p.title) from Project p where p.user.email = :email")
    List<ProjectDto> getAllProjectNamesByEmail(String email);

    List<Project> findByUser(User user);
}
