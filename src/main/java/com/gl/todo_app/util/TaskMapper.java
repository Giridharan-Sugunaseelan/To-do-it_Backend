package com.gl.todo_app.util;

import com.gl.todo_app.dto.ProjectTaskDto;
import com.gl.todo_app.dto.SectionTaskDto;
import com.gl.todo_app.dto.TaskDto;
import com.gl.todo_app.entities.Task;

import java.time.LocalDate;

public class TaskMapper {
    public static Task dtoToTask(ProjectTaskDto dto){
        Task task = new Task();
        task.setTask_id(dto.getTask_id());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.getCompleted());
        task.setDate(dto.getDate());
        task.setDueDate(dto.getDueDate());
        return task;
    }

    public static Task dtoToTask(SectionTaskDto dto){
        Task task = new Task();
        task.setTask_id(dto.getTask_id());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.getCompleted());
        task.setDate(dto.getDate());
        task.setDueDate(dto.getDueDate());
        return task;
    }

    public static ProjectTaskDto taskToProjectDto(Task task, Long project_id){
        ProjectTaskDto dto = new ProjectTaskDto();
        dto.setTask_id(task.getTask_id());
        dto.setProject_id(project_id);
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.getCompleted());
        dto.setDate(task.getDate());
        dto.setDueDate(task.getDueDate());
        return dto;
    }

    public static SectionTaskDto taskToSectionTaskDto(Task task, Long section_id){
        SectionTaskDto dto = new SectionTaskDto();
        dto.setTask_id(task.getTask_id());
        dto.setSection_id(section_id);
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.getCompleted());
        dto.setDate(task.getDate());
        dto.setDueDate(task.getDueDate());
        return dto;
    }

    public static TaskDto taskToTaskDto(Task task){
        TaskDto dto = new TaskDto();
        dto.setTask_id(task.getTask_id());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.getCompleted());
        dto.setDate(task.getDate());
        dto.setDueDate(task.getDueDate());
        return dto;
    }

    public static Task taskDtoToTask(TaskDto dto){
        Task task = new Task();
        task.setTask_id(dto.getTask_id());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.getCompleted());
        task.setDate(dto.getDate());
        task.setDueDate(dto.getDueDate());
        return task;
    }
}
