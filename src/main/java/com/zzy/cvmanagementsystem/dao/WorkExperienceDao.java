package com.zzy.cvmanagementsystem.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceDao {

    @Id
    private String id;

    private String title;

    private String startTime;

    private String endTime;

    private String company;

}
