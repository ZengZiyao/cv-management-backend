package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.StudentDto;
import com.zzy.cvmanagementsystem.service.StudentService;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.StudentServiceImpl;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cv/students")
@CrossOrigin
@Slf4j
public class StudentController {
    private StudentService studentService;
    private UserService userService;

    StudentController(StudentServiceImpl studentService, UserServiceImpl userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity getAllStudents(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUser(username).getId();

        List<StudentDto> studentDtos = studentService.getAllStudents(userid);

        return ResponseEntity.ok(studentDtos);
    }

    @GetMapping("/masters")
    public ResponseEntity getAllMasterStudents(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUser(username).getId();

        List<StudentDto> studentDtos = studentService.getAllMasterStudents(userid);

        return ResponseEntity.ok(studentDtos);
    }

    @GetMapping("/phds")
    public ResponseEntity getAllPhdStudents(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUser(username).getId();

        List<StudentDto> studentDtos = studentService.getAllPhdStudents(userid);

        return ResponseEntity.ok(studentDtos);
    }

    @PostMapping("")
    public ResponseEntity addStudent(@RequestBody StudentDto studentDto, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUser(username).getId();

        studentService.addStudent(studentDto, userid);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{studentId}")
    public ResponseEntity updateStudent(@PathVariable String studentId, @RequestBody StudentDto studentDto) {
        studentService.updateStudentById(studentId, studentDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable String studentId) {
        studentService.deleteStudentById(studentId);

        return ResponseEntity.noContent().build();
    }

}
