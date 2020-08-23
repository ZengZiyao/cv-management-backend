package com.zzy.cvmanagementsystem.dto;

import com.zzy.cvmanagementsystem.dao.CvDao;
import com.zzy.cvmanagementsystem.model.JobExperience;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CvResponse {

    private String id;

    private String name;

    private String designation;

    private String email;

    private String biography;

    private byte[] avatar;

    private List<String> awards;

    private List<String> publications;

    private List<JobExperience> jobExperiences;

    public static CvResponse fromDao(CvDao cvDao) {
        return new CvResponse(cvDao.getId(), cvDao.getName(), cvDao.getDesignation(), cvDao.getEmail(), cvDao.getBiography(), cvDao.getAvatar(), cvDao.getAwards(), cvDao.getPublications(), cvDao.getJobExperiences());
    }

}
