package com.skillval.SkillVal.service.volunteerService;

import com.skillval.SkillVal.dtos.volunteerDTOS.VolunteerInputDTO;
import com.skillval.SkillVal.dtos.volunteerDTOS.VolunteerOutputDTO;
import com.skillval.SkillVal.entity.enums.AvailabilityStatus;

import java.util.List;

public interface VolunteerService {
    VolunteerOutputDTO postVolunteer(VolunteerInputDTO volunteerInputDTO, AvailabilityStatus status);

    List<VolunteerOutputDTO> getAllVolunteers();

    void changeStatusToBusy(Long id);
}
