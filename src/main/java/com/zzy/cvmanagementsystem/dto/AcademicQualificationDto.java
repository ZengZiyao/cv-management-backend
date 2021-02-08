package com.zzy.cvmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AcademicQualificationDto {
    private String id;

    private Date year;

    private String degree;

    private String major;

    private String university;
}
