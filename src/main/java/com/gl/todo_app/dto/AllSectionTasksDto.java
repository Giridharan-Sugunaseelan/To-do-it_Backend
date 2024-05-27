package com.gl.todo_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllSectionTasksDto {
    private Long section_id;
    private List<SectionTaskDto> tasks;
}
