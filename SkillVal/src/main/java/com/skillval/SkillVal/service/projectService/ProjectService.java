package com.skillval.SkillVal.service.projectService;

import com.skillval.SkillVal.dtos.projectDTOS.ProjectInputDTO;
import com.skillval.SkillVal.dtos.projectDTOS.ProjectOutputDTO;
import com.skillval.SkillVal.entity.enums.ProjectStatus;
import com.skillval.SkillVal.entity.enums.SkillType;

import java.util.List;

public interface ProjectService {
    ProjectOutputDTO postProject(ProjectInputDTO projectInputDTO, SkillType skillType);

    List<ProjectOutputDTO> getAllProjects();

    ProjectOutputDTO assignProject(Long projectId, Long volunteerID);

    void changeStatus(Long projectId);

    List<ProjectOutputDTO> filteredProjects(SkillType skillType, ProjectStatus projectStatus);
}
