package com.zzy.cvmanagementsystem.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class StudentDao {
    @Id
    private String id;

    private String name;

    private String type;

    private Date startYear;

    private Date endYear;

    private String role;

    private String title;

    private String status;
}
