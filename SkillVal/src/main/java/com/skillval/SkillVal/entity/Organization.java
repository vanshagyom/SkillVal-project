package com.skillval.SkillVal.entity;

import com.skillval.SkillVal.entity.enums.VerifiedStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "organization")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VerifiedStatus verifiedStatus = VerifiedStatus.UNVERIFIED;

    @OneToMany(mappedBy = "organization")
    private Set<Project> projects;
}
