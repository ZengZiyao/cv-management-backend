package com.zzy.cvmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String id;

    private String name;

    private String type;

    private Date startYear;

    private Date endYear;

    private String role;

    private String title;

    private String status;

}
