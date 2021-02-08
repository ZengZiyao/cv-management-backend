package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.CourseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<CourseDto> getAllCourses();

    void updateCourseById(String id, CourseDto courseDto);

    void addCourse(CourseDto courseDto);

    void deleteCourse(String id);

}
