package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dao.StudentDao;
import com.zzy.cvmanagementsystem.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<StudentDao> getAllStudents();

    List<StudentDao> getAllMasterStudents();

    List<StudentDao> getAllPhdStudents();

    void updateStudentById(String id, StudentDto studentDto);

    void addStudent(StudentDto studentDto);

    void deleteStudentById(String id);

}
