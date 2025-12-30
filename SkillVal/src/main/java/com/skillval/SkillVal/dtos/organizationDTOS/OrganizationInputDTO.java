package com.skillval.SkillVal.dtos.organizationDTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizationInputDTO {

    @NotBlank(message = "Organization name is required")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "invalid email format")
    private String email;

    @NotBlank(message = "description is required")
    private String description;

    @NotBlank(message = "location is required")
    private String location;
}
