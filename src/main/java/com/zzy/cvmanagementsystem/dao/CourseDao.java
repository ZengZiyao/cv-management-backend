package com.zzy.cvmanagementsystem.dao;

import com.zzy.cvmanagementsystem.model.CourseLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Setter
public class CourseDao {
    @Id
    private String id;

    private String courseCode;

    private String title;

    private Date startYear;

    private Date endYear;

    private CourseLevel courseLevel;

    private String courseType;

    private int semester;
}
