package com.zzy.cvmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WorkExperienceDto {

    private String id;

    private String title;

    private Date startTime;

    private Date endTime;

    private String company;
}
