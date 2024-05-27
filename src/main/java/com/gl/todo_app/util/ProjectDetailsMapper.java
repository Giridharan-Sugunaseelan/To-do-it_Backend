package com.gl.todo_app.util;

import com.gl.todo_app.dto.*;
import com.gl.todo_app.entities.Project;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProjectDetailsMapper {
    public static ProjectDetailsDto projectToProjectDetails(Project project,
                                                            List<ProjectDto> projects,
                                                            List<TaskDto> tasks,
                                                            Set<SectionDetailsDto> sectionDto){
        ProjectDetailsDto dto = new ProjectDetailsDto();
        dto.setProject_id(project.getProject_id());
        dto.setTitle(project.getTitle());
        dto.setTasks(tasks);
        dto.setSections(sectionDto);
        dto.setProjects(projects);
        return dto;
    }
}
