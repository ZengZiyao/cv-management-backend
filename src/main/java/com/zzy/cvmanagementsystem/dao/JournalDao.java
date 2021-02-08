package com.zzy.cvmanagementsystem.dao;

import com.zzy.cvmanagementsystem.model.PubSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@AllArgsConstructor
@Document
@Getter
@Setter
public class JournalDao extends PubSource {
    @Id
    private String id;
    @Field("journalName")
    private String name;

}
