package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.CourseDao;
import com.zzy.cvmanagementsystem.dto.CourseDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.CourseRepository;
import com.zzy.cvmanagementsystem.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDto> getAllCourses(String userid) {
        List<CourseDto> courseDtoList = new ArrayList<>();
        List<CourseDao> courseDaoList = courseRepository.findAllByUserId(userid);
        for (CourseDao courseDao : courseDaoList) {
            if (courseDao.getEndYear() != null) {
                LocalDateTime dateTime = LocalDateTime.now().minusYears(2);
                LocalDateTime endYear = courseDao.getEndYear().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                if (dateTime.isAfter(endYear)) {
                    courseRepository.delete(courseDao);
                    continue;
                }
            }
            CourseDto courseDto = new CourseDto(courseDao.getId(), courseDao.getCourseCode(), courseDao.getTitle(), courseDao.getStartYear(), courseDao.getEndYear(), courseDao.getCourseLevel(), courseDao.getCourseType(), courseDao.getSemester());
            courseDtoList.add(courseDto);
        }
        return courseDtoList;
    }

    @Override
    public void updateCourseById(String id, CourseDto courseDto) {
        CourseDao courseDao = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("course not found"));
        courseDao.setCourseCode(courseDto.getCourseCode());
        courseDao.setTitle(courseDto.getTitle());
        courseDao.setSemester(courseDto.getSemester());
        courseDao.setStartYear(courseDto.getStartYear());
        courseDao.setEndYear(courseDto.getEndYear());
        courseDao.setCourseLevel(courseDto.getCourseLevel());
        courseDao.setCourseType(courseDto.getCourseType());
        courseRepository.save(courseDao);

    }

    @Override
    public void addCourse(CourseDto courseDto, String userid) {
        CourseDao courseDao = new CourseDao();
        courseDao.setCourseCode(courseDto.getCourseCode());
        courseDao.setTitle(courseDto.getTitle());
        courseDao.setSemester(courseDto.getSemester());
        courseDao.setStartYear(courseDto.getStartYear());
        courseDao.setEndYear(courseDto.getEndYear());
        courseDao.setCourseLevel(courseDto.getCourseLevel());
        courseDao.setCourseType(courseDto.getCourseType());
        courseDao.setUserId(userid);
        courseRepository.save(courseDao);

    }

    @Override
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void deleteByUserId(String userid) {
        courseRepository.deleteAll(courseRepository.findAllByUserId(userid));
    }
}
