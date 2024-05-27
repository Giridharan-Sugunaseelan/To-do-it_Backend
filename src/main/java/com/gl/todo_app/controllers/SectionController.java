package com.gl.todo_app.controllers;


import com.gl.todo_app.dto.SectionDetailsDto;
import com.gl.todo_app.dto.SectionDto;
import com.gl.todo_app.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/section")
@AllArgsConstructor
public class SectionController {

    private SectionService sectionService;

    @PostMapping
    public ResponseEntity<SectionDto> addSection(@RequestBody SectionDto dto){
        SectionDto sectionDto = this.sectionService.addSection(dto);
        return new ResponseEntity<>(sectionDto, HttpStatus.OK);
    }

    @PutMapping("/{sectionId}")
    public ResponseEntity<SectionDto> updateSection(@RequestBody SectionDto dto, @PathVariable Long sectionId){
        SectionDto sectionDto = this.sectionService.updateSection(dto, sectionId);
        return new ResponseEntity<>(sectionDto, HttpStatus.OK);
    }

    @DeleteMapping("/{sectionId}")
    public ResponseEntity<Set<SectionDetailsDto>> deleteSection(@PathVariable Long sectionId){
        Set<SectionDetailsDto> sectionDetailsDto = this.sectionService.deleteSection(sectionId);
        return new ResponseEntity<>(sectionDetailsDto, HttpStatus.OK);
    }
}
