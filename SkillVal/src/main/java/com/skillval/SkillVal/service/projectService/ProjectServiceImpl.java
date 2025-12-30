package com.skillval.SkillVal.service.projectService;

import com.skillval.SkillVal.dtos.projectDTOS.ProjectInputDTO;
import com.skillval.SkillVal.dtos.projectDTOS.ProjectOutputDTO;
import com.skillval.SkillVal.entity.Project;
import com.skillval.SkillVal.entity.Volunteer;
import com.skillval.SkillVal.entity.enums.ProjectStatus;
import com.skillval.SkillVal.entity.enums.SkillType;
import com.skillval.SkillVal.exceptions.ResourceNotFoundException;
import com.skillval.SkillVal.repository.ProjectRepository;
import com.skillval.SkillVal.repository.VolunteerRepository;
import com.skillval.SkillVal.utils.mappers.ProjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    private final VolunteerRepository volunteerRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, VolunteerRepository volunteerRepository) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public ProjectOutputDTO postProject(ProjectInputDTO projectInputDTO, SkillType skillType) {
        Project project = projectMapper.toEntity(projectInputDTO);
        project.setRequiredSkill(skillType);
        Project save = projectRepository.save(project);
        return projectMapper.toDto(save);
    }

    @Override
    public List<ProjectOutputDTO> getAllProjects() {
        List<Project> all = projectRepository.findAll();
        List<ProjectOutputDTO> list = all.stream().map(projectMapper::toDto).toList();
        return list;
    }

    @Override
    public ProjectOutputDTO assignProject(Long projectId, Long volunteerID) {

        Volunteer volunteer = volunteerRepository.findById(volunteerID)
                .orElseThrow(() -> new ResourceNotFoundException("Didn't find the volunteer with id: " + volunteerID));
        Project project = projectRepository.findById(projectId)
                        .orElseThrow(() -> new ResourceNotFoundException("Didn't find the project with id: " + projectId));

        long count = volunteer.getProjects().stream().filter(p -> p.getProjectStatus() == ProjectStatus.IN_PROGRESS).count();
        if (count > 3){
            throw new RuntimeException("Excess of Projects in Progress");
        }

        project.getVolunteers().add(volunteer);
        projectRepository.save(project);

        return projectMapper.toDto(project);
    }

    @Override
    public void changeStatus(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        if (project.getProjectStatus() == ProjectStatus.DRAFT){
            project.setProjectStatus(ProjectStatus.RECRUITING);
        }
        else if (project.getProjectStatus() == ProjectStatus.RECRUITING){
            project.setProjectStatus(ProjectStatus.IN_PROGRESS);
        } else if (project.getProjectStatus() == ProjectStatus.IN_PROGRESS) {
            project.setProjectStatus(ProjectStatus.COMPLETED);
        }
        else {
            throw new RuntimeException("Status is Completed");
        }
        projectRepository.save(project);
    }

    @Override
    public List<ProjectOutputDTO> filteredProjects(SkillType skillType, ProjectStatus projectStatus) {
        List<Project> all = projectRepository.findAll();

        List<ProjectOutputDTO> list = all.stream()
                .filter(x -> x.getRequiredSkill() == skillType && x.getProjectStatus() == projectStatus)
                .map(projectMapper::toDto)
                .toList();

        return list;
    }
}
