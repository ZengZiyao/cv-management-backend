package com.zzy.cvmanagementsystem.dto;

import com.zzy.cvmanagementsystem.model.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MembershipDto {
    private String id;

    private Date startTime;

    private Date endTime;

    private String designation;

    private String institution;

    private Country country;

    private String state;

}
