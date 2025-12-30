package com.skillval.SkillVal.entity;

import com.skillval.SkillVal.entity.enums.AvailabilityStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "volunteer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AvailabilityStatus availabilityStatus;

    @ManyToMany(mappedBy = "volunteers")
    private Set<Project> projects;
}
