package com.gl.todo_app.controllers;

import com.gl.todo_app.dto.*;
import com.gl.todo_app.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class TaskController {

    private TaskService service;


    @GetMapping("/task/today")
    public ResponseEntity<List<TaskDto>> todayTasks(){
        List<TaskDto> todayTasks = this.service.getTodayTasks();
        return new ResponseEntity<>(todayTasks, HttpStatus.OK);
    }

    @PostMapping("/projectTask")
    public ResponseEntity<ProjectTaskDto> addTask(@RequestBody ProjectTaskDto dto){
        ProjectTaskDto savedTask = this.service.addProjectTask(dto);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @PutMapping("/task/{taskId}")
    public ResponseEntity<TaskDto> completeTask(@PathVariable Long taskId){
        TaskDto taskDto = this.service.completeTask(taskId);
        return ResponseEntity.ok(taskDto);
    }

    @PutMapping("/sectionTask")
    public ResponseEntity<TaskDto> completeSectionTask(@RequestParam Long taskId, @RequestParam Long sectionId){
        SectionTaskDto sectionTaskDto = this.service.completeSectionTask(taskId, sectionId);
        return ResponseEntity.ok(sectionTaskDto);
    }

    @PutMapping("/projectTask/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto dto, @PathVariable Long taskId){
        TaskDto updateTask = this.service.updateProjectTask(dto, taskId);
        System.out.println(updateTask);
        return new ResponseEntity<>(updateTask, HttpStatus.OK);
    }

    @DeleteMapping("/projectTask/{taskId}")
    public ResponseEntity<Boolean> deleteProjectTask(@PathVariable Long taskId){
        Boolean status = this.service.deleteProjectTask(taskId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable Long id){
        Long status = this.service.deleteTask(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("/sectionTask/{taskId}")
    public ResponseEntity<Boolean> deleteSectionTask(@PathVariable Long taskId){
        Boolean status = this.service.deleteSectionTask(taskId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/sectionTask/{id}")
    public ResponseEntity<AllSectionTasksDto> getSectionTasks(@PathVariable Long id){
        AllSectionTasksDto sectionTasks = this.service.getSectionTasks(id);
        return new ResponseEntity<>(sectionTasks, HttpStatus.OK);
    }

    @GetMapping("/projectTask/{id}")
    public ResponseEntity<List<TaskDto>> getProjectTasks(@PathVariable Long id){
        List<TaskDto> projectTasks = this.service.getProjectTasks(id);
        return new ResponseEntity<>(projectTasks, HttpStatus.OK);
    }

    @PostMapping("/sectionTask")
    public ResponseEntity<SectionTaskDto> addTask(@RequestBody SectionTaskDto dto){
        SectionTaskDto sectionTaskDto = this.service.addSectionTask(dto);
        return new ResponseEntity<>(sectionTaskDto, HttpStatus.OK);
    }

    @PutMapping("/sectionTask/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody SectionTaskDto dto, @PathVariable Long taskId){
        TaskDto sectionTaskDto = this.service.updateSectionTask(dto, taskId);
        return new ResponseEntity<>(sectionTaskDto, HttpStatus.OK);
    }

}
