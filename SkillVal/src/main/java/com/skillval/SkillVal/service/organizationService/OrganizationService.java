package com.skillval.SkillVal.service.organizationService;

import com.skillval.SkillVal.dtos.organizationDTOS.OrganizationInputDTO;
import com.skillval.SkillVal.dtos.organizationDTOS.OrganizationOutputDTO;
import com.skillval.SkillVal.dtos.organizationDTOS.OrganizationStatsDTO;
import com.skillval.SkillVal.entity.enums.VerifiedStatus;

import java.util.List;

public interface OrganizationService {
    OrganizationOutputDTO addOrganization(OrganizationInputDTO organizationInputDTO, VerifiedStatus status);
    List<OrganizationOutputDTO> getAllOrgs();
    OrganizationStatsDTO getStatsFOrOrganizaton(Long id);
}
