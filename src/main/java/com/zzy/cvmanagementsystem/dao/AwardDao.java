package com.zzy.cvmanagementsystem.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Setter
public class AwardDao {
    @Id
    private String id;

    private String content;

    private Date date;

    private String userId;
}
