package com.zzy.cvmanagementsystem.dao;

import com.zzy.cvmanagementsystem.model.PubSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConferenceDao extends PubSource {
    @Id
    private String id;

    @Field("conferenceName")
    private String name;

    private String userId;
}
