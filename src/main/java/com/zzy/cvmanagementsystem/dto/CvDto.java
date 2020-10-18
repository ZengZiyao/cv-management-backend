package com.zzy.cvmanagementsystem.dto;

import com.zzy.cvmanagementsystem.model.WorkExperience;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CvDto {
    private String name;

    private String designation;

    private String email;

    private String biography;

    private byte[] avatar;

    private List<String> awards;

    private List<String> publications;

    private List<WorkExperience> workExperiences;
}
