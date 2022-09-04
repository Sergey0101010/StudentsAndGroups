package com.sergey.studentsandgroups;

import com.sergey.studentsandgroups.dto.AddGroupRequestDto;
import com.sergey.studentsandgroups.dto.AllGroupResponseDto;
import com.sergey.studentsandgroups.dto.SingleGroupResponseDto;
import com.sergey.studentsandgroups.entity.Group;
import com.sergey.studentsandgroups.repository.GroupRepository;
import com.sergey.studentsandgroups.service.GroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GroupServiceIntegrationTests {

    @MockBean
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @Test
    public void groupServiceGetByIdTest() {
        Group group = new Group();
        group.setGroupNumber("123");
        group.setCreationDate(LocalDate.now());
        group.setId(1L);

        when(groupRepository.findById(1L))
                .thenReturn(Optional.of(group));

        SingleGroupResponseDto groupByIdDto = groupService.getGroupById(1L);

        assertEquals("123", groupByIdDto.getNumber());
        assertEquals(1L, groupByIdDto.getGroupId());
    }

    @Test
    public void groupServiceAddNewGroup() {
        AddGroupRequestDto addGroupRequestDto = new AddGroupRequestDto("123");
        Group group = new Group();
        group.setGroupNumber(addGroupRequestDto.getNumber());
        group.setCreationDate(LocalDate.now());
        when(groupRepository.save(group)).thenReturn(group);

        groupService.addGroup(addGroupRequestDto);

        verify(groupRepository).save(group);

    }

    @Test
    public void groupServiceGetAllGroups() {
        Group group1 = new Group(1L, "123", LocalDate.EPOCH, new ArrayList<>());
        Group group2 = new Group(2L, "321", LocalDate.of(1989, 3, 3), new ArrayList<>());
        List<Group> allGroups = new ArrayList<>();
        allGroups.add(group1);
        allGroups.add(group2);

        when(groupRepository.findAll()).thenReturn(allGroups);

        List<AllGroupResponseDto> allGroupResponseDtos = groupService.getAllGroups();

        assertEquals(group1.getGroupNumber(), allGroupResponseDtos.get(0).getNumber());
        assertEquals(group1.getId(), allGroupResponseDtos.get(0).getId());
        assertEquals(allGroups.size(), allGroupResponseDtos.size());
    }
}
