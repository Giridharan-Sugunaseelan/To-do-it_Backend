package com.gl.todo_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long task_id;
    private String title;
    private String description;
    private Boolean completed;
    private LocalDate date;
    private LocalDate dueDate;
}
