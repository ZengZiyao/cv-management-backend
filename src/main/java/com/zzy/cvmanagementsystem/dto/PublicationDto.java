package com.zzy.cvmanagementsystem.dto;

import com.zzy.cvmanagementsystem.dao.JournalDao;
import com.zzy.cvmanagementsystem.model.Author;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PublicationDto {
    private List<Author> authors;

    private String title;

    private String page;

    private Date date;

    private JournalDao journal;

    private String tier;

}
