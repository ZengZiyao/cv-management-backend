package com.zzy.cvmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjectDto {

    private String id;

    private String title;

    private Date startYear;

    private Date endYear;

    private String role;

    private int fundingAmount;

    private String funder;

    private boolean external;

}
