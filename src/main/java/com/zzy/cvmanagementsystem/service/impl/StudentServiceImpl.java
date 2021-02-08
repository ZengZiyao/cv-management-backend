package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.StudentDao;
import com.zzy.cvmanagementsystem.dto.StudentDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.StudentRepository;
import com.zzy.cvmanagementsystem.service.StudentService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDao> getAllStudents() {
        List<StudentDao> studentDaoList = studentRepository.findAll();
        for (StudentDao studentDao : studentDaoList) {
            if (studentDao.getEndYear() != null) {
                LocalDateTime dateTime = LocalDateTime.now().minusYears(2);
                LocalDateTime endYear = studentDao.getEndYear().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                if (dateTime.isAfter(endYear)) {
                    studentRepository.delete(studentDao);
                    continue;
                }
            }
        }
        return studentRepository.findAll();
    }

    @Override
    public List<StudentDao> getAllMasterStudents() {
        StudentDao studentDao = new StudentDao();
        studentDao.setType("master");
        return studentRepository.findAll(Example.of(studentDao));
    }

    @Override
    public List<StudentDao> getAllPhdStudents() {
        StudentDao studentDao = new StudentDao();
        studentDao.setType("phd");
        return studentRepository.findAll(Example.of(studentDao));
    }

    @Override
    public void updateStudentById(String id, StudentDto studentDto) {
        StudentDao studentDao = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("student not found"));
        studentDao.setEndYear(studentDto.getEndYear());
        studentDao.setStartYear(studentDto.getStartYear());
        studentDao.setStatus(studentDto.getStatus());
        studentDao.setTitle(studentDto.getTitle());
        studentDao.setRole(studentDto.getRole());
        studentDao.setName(studentDto.getName());
        studentRepository.save(studentDao);
    }

    @Override
    public void addStudent(StudentDto studentDto) {
        StudentDao studentDao = new StudentDao();
        studentDao.setEndYear(studentDto.getEndYear());
        studentDao.setStartYear(studentDto.getStartYear());
        studentDao.setStatus(studentDto.getStatus());
        studentDao.setTitle(studentDto.getTitle());
        studentDao.setRole(studentDto.getRole());
        studentDao.setType(studentDto.getType());
        studentDao.setName(studentDto.getName());
        studentRepository.save(studentDao);
    }

    @Override
    public void deleteStudentById(String id) {
        studentRepository.deleteById(id);
    }
}
