package com.skillval.SkillVal.dtos.organizationDTOS;

import com.skillval.SkillVal.entity.enums.VerifiedStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizationOutputDTO {
    private Long id;
    private String name;
    private String email;
    private String description;
    private String location;
    private VerifiedStatus verifiedStatus;
}
