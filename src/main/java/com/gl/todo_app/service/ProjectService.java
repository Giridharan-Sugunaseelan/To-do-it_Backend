package com.gl.todo_app.service;

import com.gl.todo_app.dto.*;
import com.gl.todo_app.entities.Project;
import com.gl.todo_app.entities.Section;
import com.gl.todo_app.entities.Task;
import com.gl.todo_app.entities.User;
import com.gl.todo_app.exception.ProjectNotFoundException;
import com.gl.todo_app.repository.ProjectRepository;
import com.gl.todo_app.repository.UserRepository;
import com.gl.todo_app.util.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService {

    private ProjectRepository projectRepository;

    private SectionService sectionService;

    private UserRepository userRepository;

    public void homeProject(User user){
        Project project = new Project();
        project.setTitle("Home");
        project.setUser(user);
        Project home = this.projectRepository.save(project);
        this.sectionService.homeSection(home);
        this.projectRepository.save(home);
    }

    public ProjectDetailsDto getProject(Long projectId){
        String email = Principal.getAuthentication().getName();
        Project home = this.projectRepository.getProjectsByEmail(email, projectId);
        List<TaskDto> taskDtoList = null;
        if(!(home.getTasks() == null)){
            taskDtoList = sortedListByDate(taskDtoConverter(home.getTasks()));
        }
        Set<SectionDetailsDto> sectionDto = sectionDetailsDtoConverter(home.getSection(), projectId);
        List<ProjectDto> allProjectNamesByEmail = this.projectRepository.getAllProjectNamesByEmail(email);
        return ProjectDetailsMapper.projectToProjectDetails(home, allProjectNamesByEmail, taskDtoList, sectionDto);
    }

    public List<ProjectDto> getAllProjectNames(){
        String email = Principal.getAuthentication().getName();
        return this.projectRepository.getAllProjectNamesByEmail(email);
    }

    public ProjectDto addProject(ProjectDto dto){
        Project project = ProjectMapper.dtoToProject(dto);
        User user = this.userRepository.findByEmail(Principal.getAuthentication().getName());
        project.setUser(user);
        Project savedProject = this.projectRepository.save(project);
        return ProjectMapper.projectToDto(savedProject);
    }

    public ProjectDto updateProject(ProjectDto dto, Long id){
        Project newProject = ProjectMapper.dtoToProject(dto);
        Project oldproject = this.projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found!!!"));
        oldproject.setTitle(newProject.getTitle());
        Project updatedProject = this.projectRepository.save(oldproject);
        return ProjectMapper.projectToDto(updatedProject);
    }

    public List<ProjectDto> deleteProject(Long id){
        String email = Principal.getAuthentication().getName();
        this.projectRepository.deleteById(id);
        return this.projectRepository.getAllProjectNamesByEmail(email);
    }

    public List<TaskDto> taskDtoConverter(List<Task> tasks){
        return tasks.stream()
                .map(task -> TaskMapper.taskToTaskDto(task))
                .toList();
    }

    public static List<SectionTaskDto> sectionTaskDtoConverter(List<Task> tasks, Long section_id){
        return tasks.stream().map((task) -> TaskMapper.taskToSectionTaskDto(task, section_id)).toList();
    }

    public static Set<SectionDetailsDto> sectionDetailsDtoConverter(Set<Section> sections, Long project_id){
        return sections.stream().map((section) -> {
            List<SectionTaskDto> taskDtoList = sortedByDate(sectionTaskDtoConverter(section.getTasks(), section.getSection_id()));
            return SectionDetailsMapper.sectionToDto(section, taskDtoList, project_id);
        }).collect(Collectors.toSet());
    }

    public static List<TaskDto> sortedListByDate(List<TaskDto> list){
        return list.stream().sorted((a,b) -> a.getDate().compareTo(b.getDate())).toList();
    }

    public static List<SectionTaskDto> sortedByDate(List<SectionTaskDto> list){
        return list.stream().sorted((a,b) -> a.getDate().compareTo(b.getDate())).toList();
    }
}
