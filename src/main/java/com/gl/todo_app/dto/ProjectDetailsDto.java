package com.gl.todo_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetailsDto {
    private Long project_id;
    private String title;
    private List<TaskDto> tasks = new ArrayList<>();
    private Set<SectionDetailsDto> sections = new HashSet<>();
    private List<ProjectDto> projects = new ArrayList<>();
}
