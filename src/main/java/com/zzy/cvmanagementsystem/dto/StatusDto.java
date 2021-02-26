package com.zzy.cvmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {
    @Id
    private String id;

    private boolean academicQualification;

    private boolean award;

    private boolean biography;

    private boolean course;

    private boolean profile;

    private boolean membership;

    private boolean project;

    private boolean publication;

    private boolean student;

    private boolean workExperience;

}
