package com.gl.todo_app.util;

import com.gl.todo_app.dto.SectionDetailsDto;
import com.gl.todo_app.dto.SectionTaskDto;
import com.gl.todo_app.dto.TaskDto;
import com.gl.todo_app.entities.Section;
import com.gl.todo_app.entities.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class SectionDetailsMapper {
    public static SectionDetailsDto sectionToDto(Section section, List<SectionTaskDto> tasks, Long project_id){
        SectionDetailsDto dto = new SectionDetailsDto();
        dto.setProject_id(project_id);
        dto.setSection_id(section.getSection_id());
        dto.setTitle(section.getTitle());
        dto.setTasks(tasks);
        return dto;
    }
}
