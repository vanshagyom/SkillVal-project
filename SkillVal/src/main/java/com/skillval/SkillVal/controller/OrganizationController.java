package com.skillval.SkillVal.controller;

import com.skillval.SkillVal.dtos.organizationDTOS.OrganizationInputDTO;
import com.skillval.SkillVal.dtos.organizationDTOS.OrganizationOutputDTO;
import com.skillval.SkillVal.dtos.organizationDTOS.OrganizationStatsDTO;
import com.skillval.SkillVal.entity.enums.VerifiedStatus;
import com.skillval.SkillVal.service.organizationService.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organization")

public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<OrganizationOutputDTO> createNewOrganization(
            @Valid @RequestBody OrganizationInputDTO organizationInputDTO,
            @RequestParam(defaultValue = "UNVERIFIED")VerifiedStatus status
            ){
        OrganizationOutputDTO returnOrgDto = organizationService.addOrganization(organizationInputDTO,status);
        return new ResponseEntity<>(returnOrgDto,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationOutputDTO>> getAllOrganizations(){
        List<OrganizationOutputDTO> getAllOrg = organizationService.getAllOrgs();
        return new ResponseEntity<>(getAllOrg,HttpStatus.ACCEPTED);
    }

    @GetMapping("getStats/{id}")
    public ResponseEntity<OrganizationStatsDTO> getStats(
            @PathVariable Long id
    ){
        OrganizationStatsDTO statsFOrOrganizaton = organizationService.getStatsFOrOrganizaton(id);
        return new ResponseEntity<>(statsFOrOrganizaton,HttpStatus.ACCEPTED);
    }
}
