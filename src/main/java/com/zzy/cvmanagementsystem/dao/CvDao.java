package com.zzy.cvmanagementsystem.dao;

import com.zzy.cvmanagementsystem.dto.CvDto;
import com.zzy.cvmanagementsystem.model.JobExperience;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document
@Getter
@Setter
public class CvDao {

    @Id
    @Getter
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String designation;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String biography;

    @Getter
    @Setter
    private byte[] avatar;

    @Getter
    @Setter
    private List<String> awards;

    @Getter
    @Setter
    private List<String> publications;

    @Getter
    @Setter
    private List<JobExperience> jobExperiences;

    public static CvDao fromDto(CvDto cvDto) {
        CvDao cvDao = new CvDao();
        cvDao.setName(cvDto.getName());
        cvDao.setDesignation(cvDto.getDesignation());
        cvDao.setEmail(cvDto.getEmail());
        cvDao.setBiography(cvDto.getBiography());
        cvDao.setAvatar(cvDto.getAvatar());
        cvDao.setAwards(cvDto.getAwards());
        cvDao.setPublications(cvDto.getPublications());
        cvDao.setJobExperiences(cvDto.getJobExperiences());

        return cvDao;
    }

    public void set(Map<String, Object> updates) {
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            switch (entry.getKey()) {
                case "name":
                    this.setName(entry.getValue().toString());
                    break;
                case "designation":
                    this.setDesignation(entry.getValue().toString());
                    break;
                case "email":
                    this.setEmail(entry.getValue().toString());
                    break;
                case "biography":
                    this.setBiography(entry.getValue().toString());
                    break;
                case "avatar":
                    this.setAvatar((byte[]) entry.getValue());
                    break;
                case "awards":
                    this.setAwards((List<String>) entry.getValue());
                    break;
                case "publications":
                    this.setPublications((List<String>) entry.getValue());
                    break;
                case "jobExperience":
                    this.setJobExperiences((List<JobExperience>) entry.getValue());
            }
        }
    }

}
