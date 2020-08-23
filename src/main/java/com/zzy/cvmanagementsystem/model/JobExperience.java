package com.zzy.cvmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobExperience {

    private String id;

    private String title;

    private Date startTime;

    private boolean currentlyWorking;

    private Date endTime;

    private String company;

    private String description;

}
