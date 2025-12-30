package com.skillval.SkillVal.dtos.volunteerDTOS;

import com.skillval.SkillVal.entity.enums.AvailabilityStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VolunteerOutputDTO {
    private Long id;
    private String name;
    private String email;
    private String bio;
    private AvailabilityStatus availabilityStatus;
}
