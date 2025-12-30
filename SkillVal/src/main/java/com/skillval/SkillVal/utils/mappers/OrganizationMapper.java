package com.skillval.SkillVal.utils.mappers;

import com.skillval.SkillVal.dtos.organizationDTOS.OrganizationInputDTO;
import com.skillval.SkillVal.dtos.organizationDTOS.OrganizationOutputDTO;
import com.skillval.SkillVal.entity.Organization;
import com.skillval.SkillVal.entity.enums.VerifiedStatus;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {
    public Organization toEntity(OrganizationInputDTO organizationInputDTO){
        if(organizationInputDTO == null){
            return null;
        }
        return Organization.builder()
                .name(organizationInputDTO.getName())
                .email(organizationInputDTO.getEmail())
                .description(organizationInputDTO.getDescription())
                .location(organizationInputDTO.getLocation())
                .verifiedStatus(VerifiedStatus.VERIFIED)
                .build();
    }

    public OrganizationOutputDTO toDto(Organization organization){
        if (organization == null){
            return null;
        }
        return OrganizationOutputDTO.builder()
                .id(organization.getId())
                .name(organization.getName())
                .email(organization.getEmail())
                .description(organization.getDescription())
                .location(organization.getLocation())
                .verifiedStatus(organization.getVerifiedStatus())
                .build();
    }
}
