package com.zzy.cvmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperience {

    private String id;

    private String title;

    private String startTime;

    private boolean currentlyWorking;

    private String endTime;

    private String company;

    private String description;

}
