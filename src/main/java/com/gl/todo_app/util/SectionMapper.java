package com.gl.todo_app.util;

import com.gl.todo_app.dto.SectionDto;
import com.gl.todo_app.entities.Section;

public class SectionMapper {

    public static Section dtoToSection(SectionDto dto){
        Section section = new Section();
        section.setTitle(dto.getTitle());
        return section;
    }

    public static SectionDto sectionToDto(Section section, Long project_id){
        SectionDto dto = new SectionDto();
        dto.setSection_id(section.getSection_id());
        dto.setTitle(section.getTitle());
        dto.setProject_id(project_id);
        return dto;
    }
}
