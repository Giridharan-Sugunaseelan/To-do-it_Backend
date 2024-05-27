package com.gl.todo_app.service;

import com.gl.todo_app.dto.SectionDetailsDto;
import com.gl.todo_app.dto.SectionDto;
import com.gl.todo_app.dto.SectionTaskDto;
import com.gl.todo_app.entities.Project;
import com.gl.todo_app.entities.Section;
import com.gl.todo_app.exception.ProjectNotFoundException;
import com.gl.todo_app.exception.SectionNotFoundException;
import com.gl.todo_app.repository.ProjectRepository;
import com.gl.todo_app.repository.SectionRepository;
import com.gl.todo_app.util.SectionDetailsMapper;
import com.gl.todo_app.util.SectionMapper;
import com.gl.todo_app.util.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SectionService {

    private SectionRepository sectionRepository;

    private ProjectRepository projectRepository;

    public void homeSection(Project project){
        Section section = new Section();
        section.setTitle("My Daily Routine");
        section.setProject(project);
        this.sectionRepository.save(section);
    }

    public SectionDto addSection(SectionDto dto){
        Section section = SectionMapper.dtoToSection(dto);
        Project project = this.projectRepository.findById(dto.getProject_id()).orElseThrow(() -> new ProjectNotFoundException("Project not found!!!"));
        section.setProject(project);
        Section savedSection = this.sectionRepository.save(section);
        return SectionMapper.sectionToDto(savedSection, dto.getProject_id());
    }

    public SectionDto updateSection(SectionDto dto, Long sectionId){
        Section newSection = SectionMapper.dtoToSection(dto);
        Project project = this.projectRepository.findById(dto.getProject_id()).orElseThrow(() -> new ProjectNotFoundException("Project not found!!!"));
        Section oldSection = this.sectionRepository.findById(sectionId).orElseThrow(() -> new SectionNotFoundException("Section Not Found!!!"));
        oldSection.setTitle(newSection.getTitle());
        oldSection.setProject(project);
        this.sectionRepository.save(oldSection);
        return SectionMapper.sectionToDto(oldSection, dto.getProject_id());
    }

    public Set<SectionDetailsDto> deleteSection(Long sectionId){
        Section deletedSection = this.sectionRepository.findById(sectionId).orElseThrow(() -> new SectionNotFoundException("Section not found!!!"));
        this.sectionRepository.deleteById(sectionId);
        Set<Section> sections = this.sectionRepository.getSectionsByProject(deletedSection.getProject());
        return ProjectService.sectionDetailsDtoConverter(sections, deletedSection.getProject().getProject_id());
    }


}
