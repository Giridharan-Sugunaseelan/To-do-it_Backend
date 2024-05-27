package com.gl.todo_app.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTaskDto extends TaskDto {
    private Long project_id;
}
