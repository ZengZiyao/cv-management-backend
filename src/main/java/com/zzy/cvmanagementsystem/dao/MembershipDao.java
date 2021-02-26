package com.zzy.cvmanagementsystem.dao;

import com.zzy.cvmanagementsystem.model.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Document
@Getter
@Setter
public class MembershipDao {
    @Id
    private String id;

    private Date startTime;

    private Date endTime;

    private String designation;

    private String institution;

    private Country country;

    private String state;

    private String userId;
}
