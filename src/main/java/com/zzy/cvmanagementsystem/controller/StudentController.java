package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dao.StudentDao;
import com.zzy.cvmanagementsystem.dto.StudentDto;
import com.zzy.cvmanagementsystem.service.StudentService;
import com.zzy.cvmanagementsystem.service.impl.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cv/students")
@CrossOrigin
@Slf4j
public class StudentController {
    private StudentService studentService;

    StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public ResponseEntity getAllStudents() {
        List<StudentDao> studentDaos = studentService.getAllStudents();

        return ResponseEntity.ok(studentDaos);
    }

    @GetMapping("/masters")
    public ResponseEntity getAllMasterStudents() {
        List<StudentDao> studentDaos = studentService.getAllMasterStudents();

        return ResponseEntity.ok(studentDaos);
    }

    @GetMapping("/phds")
    public ResponseEntity getAllPhdStudents() {
        List<StudentDao> studentDaos = studentService.getAllPhdStudents();

        return ResponseEntity.ok(studentDaos);
    }

    @PostMapping("")
    public ResponseEntity addStudent(@RequestBody StudentDto studentDto) {
        studentService.addStudent(studentDto);

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
