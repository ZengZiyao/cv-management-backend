package com.zzy.cvmanagementsystem.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Setter
public class ProjectDao {
    @Id
    private String id;

    private String title;

    private Date startYear;

    private Date endYear;

    private String role;

    private int fundingAmount;

    private String funder;

    private boolean external;

    private String userId;
}
