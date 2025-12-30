package com.skillval.SkillVal.controller;

import com.skillval.SkillVal.dtos.projectDTOS.ProjectInputDTO;
import com.skillval.SkillVal.dtos.projectDTOS.ProjectOutputDTO;
import com.skillval.SkillVal.entity.enums.ProjectStatus;
import com.skillval.SkillVal.entity.enums.SkillType;
import com.skillval.SkillVal.service.projectService.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectOutputDTO> postProject(
            @Valid @RequestBody ProjectInputDTO projectInputDTO,
            @RequestParam(defaultValue = "MARKETING")SkillType skillType
            ){
        ProjectOutputDTO projectOutputDTO = projectService.postProject(projectInputDTO, skillType);
        return new ResponseEntity<>(projectOutputDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectOutputDTO>> getAllProjects(){
        List<ProjectOutputDTO> allProjects = projectService.getAllProjects();
        return new ResponseEntity<>(allProjects,HttpStatus.ACCEPTED);
    }

    @PostMapping("{projectId}/assign/{volunteerId}")
    public ResponseEntity<ProjectOutputDTO> assignVolunteerToProject(
            @PathVariable Long projectId,
            @PathVariable Long volunteerId
    ){
        ProjectOutputDTO projectOutputDTO = projectService.assignProject(projectId, volunteerId);
        return new ResponseEntity<>(projectOutputDTO,HttpStatus.ACCEPTED);
    }

    @PatchMapping("updateStatus/{id}")
    public ResponseEntity<Object> changeStatus(
            @PathVariable Long id
    ){
        projectService.changeStatus(id);
        return new ResponseEntity<>("success",HttpStatus.ACCEPTED);
    }

    @GetMapping("filter")
    public ResponseEntity<List<ProjectOutputDTO>> getFilteredProjects(
            @RequestParam SkillType skillType,
            @RequestParam ProjectStatus projectStatus
            ){
        List<ProjectOutputDTO> projectOutputDTOS = projectService.filteredProjects(skillType, projectStatus);
        return new ResponseEntity<>(projectOutputDTOS, HttpStatus.ACCEPTED);
    }
}
