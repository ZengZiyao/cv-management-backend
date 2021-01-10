package com.zzy.cvmanagementsystem.dao;

import com.zzy.cvmanagementsystem.model.Author;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Getter
@Setter
public class PublicationDao {
    @Id
    private String id;

    private List<Author> authors;

    private String title;

    private String page;

    private Date date;

    private String journalId;

    private String tier;
}
