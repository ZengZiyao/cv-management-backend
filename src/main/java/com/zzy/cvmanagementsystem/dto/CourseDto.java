package com.zzy.cvmanagementsystem.dto;

import com.zzy.cvmanagementsystem.model.CourseLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private String id;

    private String courseCode;

    private String title;

    private Date startYear;

    private Date endYear;

    private CourseLevel courseLevel;

    private String courseType;

    private int semester;
}
