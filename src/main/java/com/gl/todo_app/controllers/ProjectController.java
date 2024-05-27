package com.gl.todo_app.controllers;
import com.gl.todo_app.dto.ProjectDetailsDto;
import com.gl.todo_app.dto.ProjectDto;
import com.gl.todo_app.repository.UserRepository;
import com.gl.todo_app.service.ProjectService;
import com.gl.todo_app.service.SectionService;
import com.gl.todo_app.util.Principal;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {

    private ProjectService service;

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDetailsDto> getProject(@PathVariable Long projectId){
        ProjectDetailsDto projects = this.service.getProject(projectId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProjectDto>> getAllProjectNamesByEmail(){
        List<ProjectDto> allProjectNames = this.service.getAllProjectNames();
        return new ResponseEntity<>(allProjectNames, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> addProject(@RequestBody ProjectDto dto){
        ProjectDto savedProject = this.service.addProject(dto);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto dto, @PathVariable Long projectId){
        ProjectDto updatedProject = this.service.updateProject(dto, projectId);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<List<ProjectDto>> deleteProject(@PathVariable Long projectId){
        List<ProjectDto> projects = this.service.deleteProject(projectId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
