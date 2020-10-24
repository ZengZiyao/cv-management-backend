package com.zzy.cvmanagementsystem.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class InfoDao {

    @Id
    private String id;

    private String designation;

    private  String name;

    private String email;

}
