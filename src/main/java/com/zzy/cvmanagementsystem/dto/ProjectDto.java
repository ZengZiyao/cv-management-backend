package com.zzy.cvmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {

    private String title;

    private String description;

    private int startYear;

    private int endYear;

    private String role;

    private int fundingAmount;

    private String funder;

}
