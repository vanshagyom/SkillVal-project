package com.skillval.SkillVal.dtos.projectDTOS;

import com.skillval.SkillVal.entity.enums.ProjectStatus;
import com.skillval.SkillVal.entity.enums.SkillType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProjectInputDTO {

    @NotBlank(message = "title can't be empty")
    private String title;

    @NotBlank(message = "description can't be empty")
    private String description;

    @NotNull(message = "deadline can't be empty")
    @Future(message = "deadline must be in future")
    private LocalDateTime deadline;

    @NotNull(message = "Organization Id is required")
    private Long organizationId;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Status is required")
    private ProjectStatus projectStatus;
}
