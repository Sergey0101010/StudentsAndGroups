package com.sergey.studentsandgroups.service;

import com.sergey.studentsandgroups.dto.AddStudentRequestDto;
import com.sergey.studentsandgroups.entity.Group;
import com.sergey.studentsandgroups.entity.Student;
import com.sergey.studentsandgroups.repository.GroupRepository;
import com.sergey.studentsandgroups.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final GroupRepository groupRepository;


    @Override
    public void addStudent(AddStudentRequestDto addStudentRequestDto) {
        Optional<Group> byId = groupRepository.findById(addStudentRequestDto.getGroupId());
        Group studentGroup = byId.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Student newStudent = new Student();
        newStudent.setCreationDate(LocalDate.now());
        newStudent.setSurname(addStudentRequestDto.getSurname());
        newStudent.setGroup(studentGroup);
        studentRepository.save(newStudent);

    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
