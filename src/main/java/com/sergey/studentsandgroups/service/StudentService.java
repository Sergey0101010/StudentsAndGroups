package com.sergey.studentsandgroups.service;

import com.sergey.studentsandgroups.dto.AddStudentRequestDto;

public interface StudentService {

    void addStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudent(Long id);
}
