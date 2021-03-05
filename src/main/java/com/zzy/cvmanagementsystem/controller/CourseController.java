package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.CourseDto;
import com.zzy.cvmanagementsystem.service.CourseService;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.CourseServiceImpl;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cv/courses")
@CrossOrigin
@Slf4j
public class CourseController {
    private CourseService courseService;
    private UserService userService;

    CourseController(CourseServiceImpl courseService, UserServiceImpl userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity getAllCourses(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        List<CourseDto> courseDtos = courseService.getAllCourses(userid);

        return ResponseEntity.ok(courseDtos);
    }

    @PostMapping("")
    public ResponseEntity addCourse(@RequestBody CourseDto courseDto, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        courseService.addCourse(courseDto, userid);

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
