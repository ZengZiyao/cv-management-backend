package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.CourseDto;
import com.zzy.cvmanagementsystem.service.CourseService;
import com.zzy.cvmanagementsystem.service.impl.CourseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cv/courses")
@CrossOrigin
@Slf4j
public class CourseController {
    private CourseService courseService;

    CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @GetMapping("")
    public ResponseEntity getAllCourses() {
        List<CourseDto> courseDtos = courseService.getAllCourses();

        return ResponseEntity.ok(courseDtos);
    }

    @PostMapping("")
    public ResponseEntity addCourse(@RequestBody CourseDto courseDto) {
        courseService.addCourse(courseDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{courseId}")
    public ResponseEntity updateCourse(@PathVariable String courseId, @RequestBody CourseDto courseDto) {
        courseService.updateCourseById(courseId, courseDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity deleteCourse(@PathVariable String courseId) {
        courseService.deleteCourse(courseId);

        return ResponseEntity.noContent().build();
    }

}
