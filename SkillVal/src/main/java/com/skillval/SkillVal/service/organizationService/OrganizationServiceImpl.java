package com.skillval.SkillVal.service.organizationService;

import com.skillval.SkillVal.dtos.organizationDTOS.*;
import com.skillval.SkillVal.entity.Organization;
import com.skillval.SkillVal.entity.Project;
import com.skillval.SkillVal.entity.enums.ProjectStatus;
import com.skillval.SkillVal.entity.enums.VerifiedStatus;
import com.skillval.SkillVal.exceptions.ResourceNotFoundException;
import com.skillval.SkillVal.repository.OrganizationRepository;
import com.skillval.SkillVal.utils.mappers.OrganizationMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService{

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
    }

    @Override
    public OrganizationOutputDTO addOrganization(OrganizationInputDTO organizationInputDTO, VerifiedStatus status) {
        Organization organization = organizationMapper.toEntity(organizationInputDTO);
        organization.setVerifiedStatus(status);
        Organization saveOrg = organizationRepository.save(organization);
        return organizationMapper.toDto(saveOrg);
    }

    @Override
    public List<OrganizationOutputDTO> getAllOrgs() {
        List<Organization> all = organizationRepository.findAll();
        List<OrganizationOutputDTO> list = all.stream().map(organizationMapper::toDto).toList();
        return list;
    }

    @Override
    public OrganizationStatsDTO getStatsFOrOrganizaton(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find the Stats for Organization with id: " + id));

        Set<Project> projects = organization.getProjects();

        long count = projects.stream().filter(x -> x.getProjectStatus() == ProjectStatus.COMPLETED).count();

        LocalDateTime dateTime = projects.stream().filter(x -> x.getProjectStatus() == ProjectStatus.COMPLETED)
                .map(x -> x.getDeadline())
                .max(LocalDateTime::compareTo)
                .orElse(null);

        long count1 = organization.getProjects().stream()
                .flatMap(x -> x.getVolunteers().stream())
                .map(x -> x.getId())
                .distinct()
                .count();

        long totalImpactHours = organization.getProjects().stream()
                .filter(p -> p.getProjectStatus() == ProjectStatus.COMPLETED)
                .mapToLong(p -> p.getVolunteers().size() * 10L)
                .sum();

        ImpactMetricsDTO metricsDTO = ImpactMetricsDTO.builder()
                .projectCount(count)
                .volunteersCount(count1)
                .impactHours(totalImpactHours)
                .lastCompletedAt(dateTime)
                .build();

        List<SkillBreakdown> skillBreakDown = organization.getProjects().
                stream()
                .filter(p -> p.getProjectStatus() == ProjectStatus.COMPLETED)
                .collect(Collectors.groupingBy(Project::getRequiredSkill, Collectors.counting()))
                .entrySet().stream()
                .map(e -> SkillBreakdown.builder()
                        .skillName(e.getKey())
                        .noOfEmployees(e.getValue())
                        .build())
                .toList();

        return OrganizationStatsDTO.builder()
                .organizationId(organization.getId())
                .organizationName(organization.getName())
                .impactMetricsDTO(metricsDTO)
                .skillBreakdownList(skillBreakDown)
                .build();
    }
}
