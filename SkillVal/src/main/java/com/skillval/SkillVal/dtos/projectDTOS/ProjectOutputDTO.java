package com.skillval.SkillVal.dtos.projectDTOS;

import com.skillval.SkillVal.dtos.organizationDTOS.OrganizationOutputDTO;
import com.skillval.SkillVal.entity.Organization;
import com.skillval.SkillVal.entity.enums.ProjectStatus;
import com.skillval.SkillVal.entity.enums.SkillType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProjectOutputDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private ProjectStatus projectStatus;
    private SkillType requiredSkill;
    private String organization;
}
