package com.gl.todo_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDetailsDto {
    private Long project_id;
    private Long section_id;
    private String title;
    private List<SectionTaskDto> tasks= new ArrayList<>();
}
