package com.skillval.SkillVal.dtos.organizationDTOS;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ImpactMetricsDTO {
    private Long impactHours;
    private Long projectCount;
    private Long volunteersCount;
    private LocalDateTime lastCompletedAt;
}
