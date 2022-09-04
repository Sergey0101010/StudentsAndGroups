package com.sergey.studentsandgroups;

import com.sergey.studentsandgroups.dto.AddStudentRequestDto;
import com.sergey.studentsandgroups.entity.Group;
import com.sergey.studentsandgroups.entity.Student;
import com.sergey.studentsandgroups.repository.GroupRepository;
import com.sergey.studentsandgroups.repository.StudentRepository;
import com.sergey.studentsandgroups.service.StudentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTests {
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    @DisplayName("Test adding student to group if no exception occurs")
    public void addStudentToGroup() {
        Group group = new Group();
        group.setId(1L);
        group.setGroupNumber("123");
        group.setCreationDate(LocalDate.now());
        List<Student> studentsInGroup = new ArrayList<>();
        group.setStudents(studentsInGroup);
        AddStudentRequestDto addStudentRequestDto = new AddStudentRequestDto(1L, "Ivanov");
        Student newStudent = new Student();
        newStudent.setCreationDate(LocalDate.now());
        newStudent.setSurname(addStudentRequestDto.getSurname());
        newStudent.setGroup(group);
        given(groupRepository.findById(addStudentRequestDto.getGroupId())).willReturn(Optional.of(group));

        given(studentRepository.save(newStudent)).willReturn(newStudent);

        studentService.addStudent(addStudentRequestDto);

        verify(groupRepository).findById(1L);

        assertEquals(newStudent.getGroup().getId(), addStudentRequestDto.getGroupId());

    }

    @Test
    @DisplayName("Test adding student to group if  exception occurs")
    public void addStudentToNonExistingGroup() {
        AddStudentRequestDto addStudentRequestDto = new AddStudentRequestDto(1L, "Petrov");

        given(groupRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> studentService.addStudent(addStudentRequestDto));

    }

    @Test
    @DisplayName("Test deleting student if no exceptions")
    public void deleteStudentByIdCorrect() {
        Student student = new Student();
        student.setCreationDate(LocalDate.now());
        student.setSurname("Ivanov");
        student.setId(1L);

        given(studentRepository.findById(1L)).willReturn(Optional.of(student));

        studentService.deleteStudent(1L);

        verify(studentRepository).delete(student);

    }

    @Test
    @DisplayName("Test deleting student if student not found")
    public void deleteNotExistingStudentById() {
        given(studentRepository.findById(1L)).willReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> studentService.deleteStudent(1L));
    }

}
