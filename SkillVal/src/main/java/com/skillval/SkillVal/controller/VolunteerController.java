package com.skillval.SkillVal.controller;

import com.skillval.SkillVal.dtos.volunteerDTOS.VolunteerInputDTO;
import com.skillval.SkillVal.dtos.volunteerDTOS.VolunteerOutputDTO;
import com.skillval.SkillVal.entity.enums.AvailabilityStatus;
import com.skillval.SkillVal.service.volunteerService.VolunteerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteer")
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping
    public ResponseEntity<VolunteerOutputDTO> addVolunteer(
            @Valid @RequestBody VolunteerInputDTO volunteerInputDTO,
            @RequestParam(defaultValue = "ACTIVE") AvailabilityStatus status){
        VolunteerOutputDTO volunteerOutputDTO = volunteerService.postVolunteer(volunteerInputDTO, status);
        return new ResponseEntity<>(volunteerOutputDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VolunteerOutputDTO>> getAllVolunteers(){
        List<VolunteerOutputDTO> allVolunteers = volunteerService.getAllVolunteers();
        return new ResponseEntity<>(allVolunteers,HttpStatus.ACCEPTED);
    }

    @PatchMapping("change/{id}")
    public ResponseEntity<Object> updateToBusy(
            @PathVariable Long id
    ){
        volunteerService.changeStatusToBusy(id);
        return new ResponseEntity<>("success",HttpStatus.ACCEPTED);
    }
}
