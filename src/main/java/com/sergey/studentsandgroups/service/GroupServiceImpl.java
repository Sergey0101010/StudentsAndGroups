package com.sergey.studentsandgroups.service;

import com.sergey.studentsandgroups.dto.AddGroupRequestDto;
import com.sergey.studentsandgroups.dto.AllGroupResponseDto;
import com.sergey.studentsandgroups.dto.GetStudentResponseDto;
import com.sergey.studentsandgroups.dto.SingleGroupResponseDto;
import com.sergey.studentsandgroups.entity.Group;
import com.sergey.studentsandgroups.entity.Student;
import com.sergey.studentsandgroups.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    final GroupRepository groupRepository;

    @Override
    public List<AllGroupResponseDto> getAllGroups() {
        List<Group> allGroups = groupRepository.findAll();

        return allGroups.stream()
                .sorted(Comparator.comparing(Group::getCreationDate))
                .map((this::getAllGroupResponseDtoFromGroup)).toList();
    }

    @Override
    public void addGroup(AddGroupRequestDto addGroupRequestDto) {
        Group newGroup = new Group();
        newGroup.setGroupNumber(addGroupRequestDto.getNumber());
        newGroup.setCreationDate(LocalDate.now());
        groupRepository.save(newGroup);
    }

    @Override
    public SingleGroupResponseDto getGroupById(Long id) {
        Optional<Group> groupOptionalById = groupRepository.findById(id);
        Group group = groupOptionalById.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        SingleGroupResponseDto singleGroupResponseDto = new SingleGroupResponseDto();
        singleGroupResponseDto.setGroupId(group.getId());
        singleGroupResponseDto.setNumber(group.getGroupNumber());

        List<Student> students = Optional.ofNullable(group.getStudents()).orElse(Collections.emptyList());
        singleGroupResponseDto.setStudentList(students.stream()
                .sorted(Comparator.comparing(Student::getSurname))
                .map(this::getGetStudentResponseDtoFromStudent).toList());
        return singleGroupResponseDto;

    }

    private GetStudentResponseDto getGetStudentResponseDtoFromStudent(Student student) {
        GetStudentResponseDto studentResponseDto = new GetStudentResponseDto();
        studentResponseDto.setStudentId(student.getId());
        studentResponseDto.setSurname(student.getSurname());
        studentResponseDto.setCreateAt(student.getCreationDate());
        return studentResponseDto;
    }

    private AllGroupResponseDto getAllGroupResponseDtoFromGroup(Group group) {
        AllGroupResponseDto dtoGroup = new AllGroupResponseDto();
        dtoGroup.setId(group.getId());
        dtoGroup.setNumber(group.getGroupNumber());
        dtoGroup.setQuantity(group.getStudents().size());
        return dtoGroup;
    }
}
