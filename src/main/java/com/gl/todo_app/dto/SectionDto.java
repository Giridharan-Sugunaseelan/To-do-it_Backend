package com.gl.todo_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {
    private Long project_id;
    private Long section_id;
    private String title;
    private List<SectionTaskDto> tasks = new ArrayList<>();
}
