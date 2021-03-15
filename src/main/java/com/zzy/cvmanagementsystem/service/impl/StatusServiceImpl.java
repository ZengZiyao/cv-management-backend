package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.StatusDao;
import com.zzy.cvmanagementsystem.dto.StatusDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.StatusRepository;
import com.zzy.cvmanagementsystem.service.*;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    private StatusRepository statusRepository;
    private AcademicQualificationService academicQualificationService;
    private AwardService awardService;
    private CourseService courseService;
    private MembershipService membershipService;
    private InfoService infoService;
    private ProjectService projectService;
    private PublicationService publicationService;
    private StudentService studentService;
    private WorkExperienceService workExperienceService;
    private CitationService citationService;

    StatusServiceImpl(StatusRepository statusRepository,
                      AcademicQualificationServiceImpl academicQualificationService,
                      AwardServiceImpl awardService,
                      CourseServiceImpl courseService,
                      MembershipServiceImpl membershipService,
                      InfoServiceImpl infoService,
                      ProjectServiceImpl projectService,
                      PublicationServiceImpl publicationService,
                      StudentServiceImpl studentService,
                      WorkExperienceServiceImpl workExperienceService,
                      CitationServiceImpl citationService) {
        this.statusRepository = statusRepository;
        this.academicQualificationService = academicQualificationService;
        this.awardService = awardService;
        this.courseService = courseService;
        this.membershipService = membershipService;
        this.infoService = infoService;
        this.projectService = projectService;
        this.publicationService = publicationService;
        this.studentService = studentService;
        this.workExperienceService = workExperienceService;
        this.citationService = citationService;
    }

    @Override
    public StatusDto getStatus(String userid) {
        StatusDao statusDao = statusRepository.findByUserId(userid);
        if (statusDao != null) {
            StatusDto statusDto = new StatusDto();
            statusDto.setAcademicQualification(statusDao.isAcademicQualification());
            statusDto.setAward(statusDao.isAward());
            statusDto.setCourse(statusDao.isCourse());
            statusDto.setId(statusDao.getId());
            statusDto.setMembership(statusDao.isMembership());
            statusDto.setProfile(statusDao.isProfile());
            statusDto.setProject(statusDao.isProject());
            statusDto.setPublication(statusDao.isPublication());
            statusDto.setStudent(statusDao.isStudent());
            statusDto.setWorkExperience(statusDao.isWorkExperience());
            statusDto.setCitation(statusDao.isCitation());
            return statusDto;
        }

        throw new NotFoundException("Status not found");
    }

    @Override
    public void updateStatus(String userid, String id, StatusDto statusDto) {
        StatusDao statusDao = statusRepository.findById(id).orElseThrow(() -> new NotFoundException("Status not found"));
        statusDao.setAcademicQualification(statusDto.isAcademicQualification());
        statusDao.setAward(statusDto.isAward());
        statusDao.setCourse(statusDto.isCourse());
        statusDao.setMembership(statusDto.isMembership());
        statusDao.setProfile(statusDto.isProfile());
        statusDao.setProject(statusDto.isProject());
        statusDao.setPublication(statusDto.isPublication());
        statusDao.setStudent(statusDto.isStudent());
        statusDao.setWorkExperience(statusDto.isWorkExperience());
        statusDao.setCitation(statusDto.isCitation());
        statusRepository.save(statusDao);

        if (!statusDao.isAcademicQualification()) {
            academicQualificationService.deleteByUserId(userid);
        }
        if (!statusDao.isAward()) {
            awardService.deleteByUserId(userid);
        }
        if (!statusDao.isCourse()) {
            courseService.deleteByUserId(userid);
        }
        if (!statusDao.isMembership()) {
            membershipService.deleteByUserId(userid);
        }
        if (!statusDao.isProfile()) {
            infoService.deleteByUserId(userid);
        }
        if (!statusDao.isProject()) {
            projectService.deleteByUserId(userid);
        }
        if (!statusDao.isPublication()) {
            publicationService.deleteByUserId(userid);
        }
        if (!statusDao.isStudent()) {
            studentService.deleteByUserId(userid);
        }
        if (!statusDao.isWorkExperience()) {
            workExperienceService.deleteByUserId(userid);
        }

        if (statusDao.isCitation()) {
            citationService.createCitations(userid);
        } else {
            citationService.deleteCitationsByUser(userid);
        }
    }

    @Override
    public void createStatus(String userid) {
        StatusDao statusDao = new StatusDao();
        statusDao.setUserId(userid);
        statusRepository.save(statusDao);
    }
}
