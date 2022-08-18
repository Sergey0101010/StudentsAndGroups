package com.sergey.studentsandgroups.service;

import com.sergey.studentsandgroups.dto.AddGroupRequestDto;
import com.sergey.studentsandgroups.dto.AllGroupResponseDto;
import com.sergey.studentsandgroups.dto.SingleGroupResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GroupService {

    List<AllGroupResponseDto> getAllGroups();

    void addGroup(AddGroupRequestDto addGroupRequestDto);

    SingleGroupResponseDto getGroupById(Long id);
}
