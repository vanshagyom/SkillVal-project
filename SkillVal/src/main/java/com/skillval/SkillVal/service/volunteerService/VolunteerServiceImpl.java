package com.skillval.SkillVal.service.volunteerService;

import com.skillval.SkillVal.dtos.volunteerDTOS.VolunteerInputDTO;
import com.skillval.SkillVal.dtos.volunteerDTOS.VolunteerOutputDTO;
import com.skillval.SkillVal.entity.Volunteer;
import com.skillval.SkillVal.entity.enums.AvailabilityStatus;
import com.skillval.SkillVal.exceptions.ResourceNotFoundException;
import com.skillval.SkillVal.repository.VolunteerRepository;
import com.skillval.SkillVal.utils.mappers.VolunteerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService{

    private final VolunteerRepository volunteerRepository;

    private final VolunteerMapper volunteerMapper;

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository, VolunteerMapper volunteerMapper) {
        this.volunteerRepository = volunteerRepository;
        this.volunteerMapper = volunteerMapper;
    }

    @Override
    public VolunteerOutputDTO postVolunteer(VolunteerInputDTO volunteerInputDTO, AvailabilityStatus status) {
        Volunteer entity = volunteerMapper.toEntity(volunteerInputDTO);
        entity.setAvailabilityStatus(status);
        Volunteer save = volunteerRepository.save(entity);
        return volunteerMapper.toDto(save);
    }

    @Override
    public List<VolunteerOutputDTO> getAllVolunteers() {
        List<Volunteer> all = volunteerRepository.findAll();
        List<VolunteerOutputDTO> list = all.stream().map(volunteerMapper::toDto).toList();
        return list;
    }

    @Override
    public void changeStatusToBusy(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Didn't find volunteer with id:" + id));

        volunteer.setAvailabilityStatus(AvailabilityStatus.BUSY);

        volunteerRepository.save(volunteer);

    }
}
