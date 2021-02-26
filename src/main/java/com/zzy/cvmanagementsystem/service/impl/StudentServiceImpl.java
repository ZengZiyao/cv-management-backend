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
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> getAllStudents(String userid) {
        List<StudentDao> studentDaoList = studentRepository.findAllByUserId(userid);
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
        List<StudentDto> studentDtoList = new ArrayList<>();
        studentDaoList = studentRepository.findAll();
        for (StudentDao studentDao : studentDaoList) {
            StudentDto studentDto = new StudentDto(studentDao.getId(), studentDao.getName(), studentDao.getType(), studentDao.getStartYear(), studentDao.getEndYear(), studentDao.getRole(), studentDao.getTitle(), studentDao.getStatus());
            studentDtoList.add(studentDto);
        }

        return studentDtoList;
    }

    @Override
    public List<StudentDto> getAllMasterStudents(String userid) {
        StudentDao example = new StudentDao();
        example.setType("master");
        example.setUserId(userid);
        List<StudentDao> studentDaoList = studentRepository.findAll(Example.of(example));
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (StudentDao studentDao : studentDaoList) {
            StudentDto studentDto = new StudentDto(studentDao.getId(), studentDao.getName(), studentDao.getType(), studentDao.getStartYear(), studentDao.getEndYear(), studentDao.getRole(), studentDao.getTitle(), studentDao.getStatus());
            studentDtoList.add(studentDto);
        }

        return studentDtoList;

    }

    @Override
    public List<StudentDto> getAllPhdStudents(String userid) {
        StudentDao example = new StudentDao();
        example.setType("phd");
        example.setUserId(userid);
        List<StudentDao> studentDaoList = studentRepository.findAll(Example.of(example));
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (StudentDao studentDao : studentDaoList) {
            StudentDto studentDto = new StudentDto(studentDao.getId(), studentDao.getName(), studentDao.getType(), studentDao.getStartYear(), studentDao.getEndYear(), studentDao.getRole(), studentDao.getTitle(), studentDao.getStatus());
            studentDtoList.add(studentDto);
        }

        return studentDtoList;
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
    public void addStudent(StudentDto studentDto, String userid) {
        StudentDao studentDao = new StudentDao();
        studentDao.setEndYear(studentDto.getEndYear());
        studentDao.setStartYear(studentDto.getStartYear());
        studentDao.setStatus(studentDto.getStatus());
        studentDao.setTitle(studentDto.getTitle());
        studentDao.setRole(studentDto.getRole());
        studentDao.setType(studentDto.getType());
        studentDao.setName(studentDto.getName());
        studentDao.setUserId(userid);
        studentRepository.save(studentDao);
    }

    @Override
    public void deleteStudentById(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void deleteByUserId(String userid) {
        studentRepository.deleteAll(studentRepository.findAllByUserId(userid));
    }
}
