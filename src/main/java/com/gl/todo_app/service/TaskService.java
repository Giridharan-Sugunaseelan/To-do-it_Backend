package com.gl.todo_app.service;

import com.gl.todo_app.dto.AllSectionTasksDto;
import com.gl.todo_app.dto.ProjectTaskDto;
import com.gl.todo_app.dto.SectionTaskDto;
import com.gl.todo_app.dto.TaskDto;
import com.gl.todo_app.entities.Project;
import com.gl.todo_app.entities.Section;
import com.gl.todo_app.entities.Task;
import com.gl.todo_app.entities.User;
import com.gl.todo_app.exception.ProjectNotFoundException;
import com.gl.todo_app.exception.SectionNotFoundException;
import com.gl.todo_app.exception.TaskNotFoundException;
import com.gl.todo_app.repository.ProjectRepository;
import com.gl.todo_app.repository.SectionRepository;
import com.gl.todo_app.repository.TaskRepository;
import com.gl.todo_app.repository.UserRepository;
import com.gl.todo_app.util.Principal;
import com.gl.todo_app.util.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    private ProjectRepository projectRepository;

    private SectionRepository sectionRepository;

    private UserRepository userRepository;

    private Task updateTask(Task task, Long id){
        Task oldTask = this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with the given id not found"));
        oldTask.setTitle(task.getTitle());
        oldTask.setDescription(task.getDescription());
        oldTask.setCompleted(task.getCompleted());
        oldTask.setDate(task.getDate());
        oldTask.setDueDate(task.getDueDate());
        return this.taskRepository.save(oldTask);
    }

    private List<ProjectTaskDto> getProjectTasks(List<Project> projects){
        return projects.stream().flatMap((project) -> project.getTasks().stream()).map((task) -> TaskMapper.taskToProjectDto(task, task.getProject().getProject_id())).toList();
    }



    private List<SectionTaskDto> getSectionTasks(List<Project> projects){
        List<Section> sections = projects.stream().flatMap((project) -> project.getSection().stream()).toList();
        return sectionTasks(sections);
    }

    private List<SectionTaskDto> sectionTasks(List<Section> sections){
        return sections.stream().flatMap((section) -> section.getTasks().stream()).map((task) -> TaskMapper.taskToSectionTaskDto(task, task.getSection().getSection_id())).toList();
    }

    private List<TaskDto> getAllTasks(String email){
        User user = this.userRepository.findByEmail(email);
        List<Project> projects = this.projectRepository.findByUser(user);
        List<ProjectTaskDto> projectTasks1 = getProjectTasks(projects);
        List<SectionTaskDto> sectionTasks1 = getSectionTasks(projects);
        List<TaskDto> allTasks= new ArrayList<>(projectTasks1);
        allTasks.addAll(sectionTasks1);
        return allTasks.stream().sorted((a, b) -> a.getDueDate().compareTo(b.getDueDate())).toList();
    }

    public TaskDto completeTask(Long id){
        Task oldTask = this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with the given id not found"));
        oldTask.setCompleted((!oldTask.getCompleted()));
        Task save = this.taskRepository.save(oldTask);
        return TaskMapper.taskToTaskDto(save);
    }

    public ProjectTaskDto addProjectTask(ProjectTaskDto dto){
        Task task = TaskMapper.dtoToTask(dto);
        System.out.println("Tasks before adding date:" + task);
        task.setDate(LocalDate.now());
        System.out.println("Tasks after adding date:" + task);
        Project project = projectRepository.findById(dto.getProject_id()).orElseThrow(() -> new ProjectNotFoundException("Project Not Found!"));
        task.setProject(project);
        Task savedTask = this.taskRepository.save(task);
        return TaskMapper.taskToProjectDto(savedTask, dto.getProject_id());
    }

    public TaskDto updateProjectTask(TaskDto dto, Long id){
        Task newTask = TaskMapper.taskDtoToTask(dto);
        Task updatedTask = this.taskRepository.save(this.updateTask(newTask, id));
        return TaskMapper.taskToTaskDto(updatedTask);
    }

    public List<TaskDto> getTodayTasks(){
        String email = Principal.getAuthentication().getName();
        return getAllTasks(email);
//        return allTasks.stream().filter((task) -> task.getDueDate().isBefore(LocalDate.now()) && !task.getCompleted() || task.getDueDate().equals(LocalDate.now())).sorted((a,b) -> a.getDueDate().compareTo(b.getDueDate())).toList();
    }

//    public List<TaskDto> getUpcomingTask(){
//        String email = Principal.getAuthentication().getName();
//        List<TaskDto> allTasks = getAllTasks(email);
//        return allTasks.stream().filter((task) -> task.getDueDate().isAfter(LocalDate.now())).sorted((a, b) -> a.getDueDate().compareTo(b.getDueDate())).toList();
//    }

    public Boolean deleteProjectTask(Long id){
        Task task1 = this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with the given id not found"));
        this.taskRepository.delete(task1);
        return true;
    }

    public Boolean deleteSectionTask(Long id){
        Task task1 = this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with the given id not found"));
        this.taskRepository.delete(task1);
        return true;
    }

    public List<TaskDto> getProjectTasks(Long id){
        Project project = this.projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project with the given id doesn't exists!!!"));
        List<Task> tasks = project.getTasks();
        return tasks.stream()
                .map(task -> TaskMapper.taskToTaskDto(task))
                .toList();
    }

    public AllSectionTasksDto getSectionTasks (Long id){
        Section section = this.sectionRepository.findById(id).orElseThrow(() -> new SectionNotFoundException("Section with the given id doesn't exists."));
        List<Task> tasks = this.taskRepository.findAllBySection(section);
        List<SectionTaskDto> sectionTasks = tasks.stream()
                .map(task -> TaskMapper.taskToSectionTaskDto(task, task.getSection().getSection_id())).toList();
        return new AllSectionTasksDto(id, sectionTasks);
    }

    public Long deleteTask(Long id){
        this.taskRepository.deleteById(id);
        return id;
    }

    public SectionTaskDto addSectionTask(SectionTaskDto dto){
        Task task = TaskMapper.dtoToTask(dto);
        task.setDate(LocalDate.now());
        Section section = this.sectionRepository.findById(dto.getSection_id()).orElseThrow(() -> new SectionNotFoundException("Section doesn't exists!!!"));
        task.setSection(section);
        Task savedTask = this.taskRepository.save(task);
        TaskDto sectionTaskDto = TaskMapper.taskToSectionTaskDto(savedTask, dto.getSection_id());
        System.out.println(sectionTaskDto);
        return TaskMapper.taskToSectionTaskDto(savedTask, dto.getSection_id());
    }

    public TaskDto updateSectionTask(SectionTaskDto dto, Long id){
        Task newTask = TaskMapper.dtoToTask(dto);
        Task updatedTask = this.taskRepository.save(this.updateTask(newTask, id));
        TaskDto taskDto = TaskMapper.taskToSectionTaskDto(updatedTask, dto.getSection_id());
        return TaskMapper.taskToSectionTaskDto(updatedTask,dto.getSection_id());
    }
}
