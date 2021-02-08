package com.zzy.cvmanagementsystem.dto;

import com.zzy.cvmanagementsystem.model.Author;
import com.zzy.cvmanagementsystem.model.Country;
import com.zzy.cvmanagementsystem.model.PubSource;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PublicationDto {
    private String id;

    private List<Author> authors;

    private String title;

    private String page;

    private Date date;

    private int type;

    private PubSource pubSource;

    private Country Country;

    private String tier;

}
