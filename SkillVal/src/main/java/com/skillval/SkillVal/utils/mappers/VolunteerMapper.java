package com.skillval.SkillVal.utils.mappers;

import com.skillval.SkillVal.dtos.volunteerDTOS.VolunteerInputDTO;
import com.skillval.SkillVal.dtos.volunteerDTOS.VolunteerOutputDTO;
import com.skillval.SkillVal.entity.Volunteer;
import org.springframework.stereotype.Component;

@Component
public class VolunteerMapper {
    public Volunteer toEntity(VolunteerInputDTO volunteerInputDTO){
        if (volunteerInputDTO == null){
            return null;
        }

        return Volunteer.builder()
                .name(volunteerInputDTO.getName())
                .email(volunteerInputDTO.getEmail())
                .bio(volunteerInputDTO.getBio())
                .build();
    }

    public VolunteerOutputDTO toDto(Volunteer volunteer){
        if (volunteer == null){
            return null;
        }

        return VolunteerOutputDTO.builder()
                .id(volunteer.getId())
                .name(volunteer.getName())
                .bio(volunteer.getBio())
                .email(volunteer.getEmail())
                .availabilityStatus(volunteer.getAvailabilityStatus())
                .build();
    }
}
