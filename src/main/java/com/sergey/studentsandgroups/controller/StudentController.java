package com.sergey.studentsandgroups.controller;

import com.sergey.studentsandgroups.dto.AddGroupRequestDto;
import com.sergey.studentsandgroups.dto.AddStudentRequestDto;
import com.sergey.studentsandgroups.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public void addStudent(@Valid @RequestBody AddStudentRequestDto addStudentRequestDto) {
        studentService.addStudent(addStudentRequestDto);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
