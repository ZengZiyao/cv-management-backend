package com.zzy.cvmanagementsystem.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class StatusDao {
    @Id
    private String id;

    private String userId;

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
