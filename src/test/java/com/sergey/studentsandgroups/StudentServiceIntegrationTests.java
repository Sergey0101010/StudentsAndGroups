package com.sergey.studentsandgroups;

import com.sergey.studentsandgroups.dto.AddStudentRequestDto;
import com.sergey.studentsandgroups.entity.Group;
import com.sergey.studentsandgroups.entity.Student;
import com.sergey.studentsandgroups.repository.GroupRepository;
import com.sergey.studentsandgroups.repository.StudentRepository;
import com.sergey.studentsandgroups.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceIntegrationTests {

    @MockBean
    private GroupRepository groupRepository;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    @Test
    public void addStudentToGroup() {
        AddStudentRequestDto addStudentRequestDto = new AddStudentRequestDto(1L, "Ivanov");
        Group studentGroup = new Group();
        studentGroup.setId(addStudentRequestDto.getGroupId());
        studentGroup.setGroupNumber("AAA");
        studentGroup.setCreationDate(LocalDate.now());
        studentGroup.setStudents(new ArrayList<>());
        Student newStudent = new Student();
        newStudent.setSurname(addStudentRequestDto.getSurname());
        newStudent.setCreationDate(LocalDate.now());
        newStudent.setGroup(studentGroup);

        when(groupRepository.findById(1L)).thenReturn(Optional.of(studentGroup));

        studentService.addStudent(addStudentRequestDto);

        verify(studentRepository).save(newStudent);

    }

    @Test
    public void deleteStudent() {
        Student student = new Student();
        student.setCreationDate(LocalDate.now());
        student.setSurname("Ivanov");
        student.setId(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        studentService.deleteStudent(1L);

        verify(studentRepository).delete(student);

    }
}
