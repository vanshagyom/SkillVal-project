package com.skillval.SkillVal.dtos.volunteerDTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VolunteerInputDTO {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "email format is invalid")
    private String email;

    private String bio;
}
