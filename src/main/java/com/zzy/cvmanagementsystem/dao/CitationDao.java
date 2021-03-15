package com.zzy.cvmanagementsystem.dao;

import com.zzy.cvmanagementsystem.model.Database;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CitationDao {
    @Id
    private String id;

    private Database database;

    private int countWithSelf;

    private int countWithoutSelf;

    private int hindex;

    private String userId;

}
