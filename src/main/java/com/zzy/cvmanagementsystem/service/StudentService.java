package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<StudentDto> getAllStudents(String userid);

    List<StudentDto> getAllMasterStudents(String userid);

    List<StudentDto> getAllPhdStudents(String userid);

    void updateStudentById(String id, StudentDto studentDto);

    void addStudent(StudentDto studentDto, String userid);

    void deleteStudentById(String id);

    void deleteByUserId(String userid);

}
