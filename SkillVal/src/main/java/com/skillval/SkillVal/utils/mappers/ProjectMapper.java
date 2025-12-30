package com.skillval.SkillVal.utils.mappers;

import com.skillval.SkillVal.dtos.projectDTOS.ProjectInputDTO;
import com.skillval.SkillVal.dtos.projectDTOS.ProjectOutputDTO;
import com.skillval.SkillVal.entity.Organization;
import com.skillval.SkillVal.entity.Project;
import com.skillval.SkillVal.exceptions.ResourceNotFoundException;
import com.skillval.SkillVal.repository.OrganizationRepository;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    private final OrganizationRepository organizationRepository;

    public ProjectMapper(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Project toEntity(ProjectInputDTO projectInputDTO){
        if (projectInputDTO == null){
            return null;
        }

        Organization organization = organizationRepository.findById(projectInputDTO.getOrganizationId())
                .orElseThrow(() -> new ResourceNotFoundException("Didn't find the Organizaton Id"));

        return Project.builder()
                .title(projectInputDTO.getTitle())
                .description(projectInputDTO.getDescription())
                .projectStatus(projectInputDTO.getProjectStatus())
                .deadline(projectInputDTO.getDeadline())
                .organization(organization)
                .build();
    }

    public ProjectOutputDTO toDto(Project project){
        if (project == null){
            return null;
        }

        return ProjectOutputDTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .deadline(project.getDeadline())
                .description(project.getDescription())
                .projectStatus(project.getProjectStatus())
                .requiredSkill(project.getRequiredSkill())
                .organization(project.getOrganization().getName())
                .build();
    }
}
