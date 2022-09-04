package com.sergey.studentsandgroups;

import com.sergey.studentsandgroups.dto.AddGroupRequestDto;
import com.sergey.studentsandgroups.dto.AllGroupResponseDto;
import com.sergey.studentsandgroups.dto.SingleGroupResponseDto;
import com.sergey.studentsandgroups.entity.Group;
import com.sergey.studentsandgroups.entity.Student;
import com.sergey.studentsandgroups.repository.GroupRepository;
import com.sergey.studentsandgroups.service.GroupServiceImpl;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GroupServiceImplTests {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupServiceImpl groupService;

    @Test
    @DisplayName("Test the getting list of students in group by group id" +
            "if no exception occurs")
    public void getGroupByIdCorrect() {

        Group group = new Group();
        group.setId(1L);
        group.setGroupNumber("123");
        group.setCreationDate(LocalDate.now());
        List<Student> studentsInGroup = new ArrayList<>();
        Student student = new Student(1L, LocalDate.now(), "Ivanov", group);
        studentsInGroup.add(student);
        group.setStudents(studentsInGroup);

        given(groupRepository.findById(group.getId()))
                .willReturn(Optional.of(group));

        SingleGroupResponseDto groupByIdDto = groupService.getGroupById(group.getId());

        assertEquals(1L, groupByIdDto.getGroupId());
        assertEquals("123", groupByIdDto.getNumber());
        assertEquals(studentsInGroup.size(), groupByIdDto.getStudentList().size());


    }

    @Test
    @DisplayName("Test the getting list of students in group by group id" +
            "if group not found")
    public void getGroupByIdNotFound() {

        given(groupRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> groupService.getGroupById(1L));


    }

    @Test
    @DisplayName("Test the getting list of all groups if no exception occurs")
    public void getAllGroups() {
        Group group1 = new Group(1L, "123", LocalDate.EPOCH, new ArrayList<>());
        Group group2 = new Group(2L, "1234", LocalDate.of(2000, 12, 12),
                new ArrayList<>(List.of(new Student())));

        List<Group> allGroups = new ArrayList<>();
        allGroups.add(group1);
        allGroups.add(group2);

        given(groupRepository.findAll()).willReturn(allGroups);

        List<AllGroupResponseDto> allGroupsDtos = groupService.getAllGroups();

        assertEquals(2, allGroupsDtos.size());

        assertEquals("123", allGroupsDtos.get(0).getNumber());
        assertEquals("1234", allGroupsDtos.get(1).getNumber());
        assertEquals(group2.getStudents().size(), allGroupsDtos.get(1).getQuantity());
        assertEquals(group1.getId(), allGroupsDtos.get(0).getId());

    }

    @Test
    @DisplayName("Test the getting list of all groups if there are no groups in repository")
    public void getAllGroupsEmptyList() {
        given(groupRepository.findAll()).willReturn(List.of());

        List<AllGroupResponseDto> allGroupsDtos = groupService.getAllGroups();

        assertEquals(0, allGroupsDtos.size());

    }


    @Test
    @DisplayName("Test adding new group to groups list if no exception occurs")
    public void addGroupCorrect() {

        AddGroupRequestDto addGroupRequestDto = new AddGroupRequestDto("123");
        Group newGroup = new Group();
        newGroup.setGroupNumber(addGroupRequestDto.getNumber());
        newGroup.setCreationDate(LocalDate.now());
        given(groupRepository.save(newGroup)).willReturn(newGroup);
        groupService.addGroup(addGroupRequestDto);
        assertEquals("123", groupRepository.save(newGroup).getGroupNumber());

    }


}
