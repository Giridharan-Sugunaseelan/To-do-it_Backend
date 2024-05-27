package com.gl.todo_app.util;

import com.gl.todo_app.dto.ProjectDto;
import com.gl.todo_app.entities.Project;

public class ProjectMapper {

    public static ProjectDto projectToDto(Project project){
        ProjectDto dto = new ProjectDto();
        dto.setTitle(project.getTitle());
        dto.setProject_id(project.getProject_id());
        return dto;
    }

    public static Project dtoToProject(ProjectDto dto){
        Project project = new Project();
        project.setTitle(dto.getTitle());
        return project;
    }
}
