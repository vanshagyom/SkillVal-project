package com.skillval.SkillVal.dtos.organizationDTOS;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrganizationStatsDTO {
    private Long organizationId;
    private String organizationName;
    private ImpactMetricsDTO impactMetricsDTO;
    private List<SkillBreakdown> skillBreakdownList;
}
