package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.CourseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<CourseDto> getAllCourses(String userid);

    void updateCourseById(String id, CourseDto courseDto);

    void addCourse(CourseDto courseDto, String userid);

    void deleteCourse(String id);

    void deleteByUserId(String userid);

}
