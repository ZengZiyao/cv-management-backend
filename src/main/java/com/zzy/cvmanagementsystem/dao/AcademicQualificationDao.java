package com.zzy.cvmanagementsystem.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Setter
public class AcademicQualificationDao {
    @Id
    private String id;

    private Date year;

    private String degree;

    private String major;

    private String university;

    private String userId;
}
