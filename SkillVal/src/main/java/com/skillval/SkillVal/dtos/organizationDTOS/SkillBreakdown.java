package com.skillval.SkillVal.dtos.organizationDTOS;

import com.skillval.SkillVal.entity.enums.SkillType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkillBreakdown {
    private SkillType skillName;
    private Long noOfEmployees;
}
