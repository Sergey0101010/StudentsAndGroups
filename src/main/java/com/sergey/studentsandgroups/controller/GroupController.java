package com.sergey.studentsandgroups.controller;

import com.sergey.studentsandgroups.dto.AddGroupRequestDto;
import com.sergey.studentsandgroups.dto.AllGroupResponseDto;
import com.sergey.studentsandgroups.dto.SingleGroupResponseDto;
import com.sergey.studentsandgroups.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@Validated
public class GroupController {

    final GroupService groupService;

    @GetMapping
    public List<AllGroupResponseDto> getAllGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping
    public void addGroup(@Valid @RequestBody AddGroupRequestDto addGroupRequestDto) {
        groupService.addGroup(addGroupRequestDto);
    }

    @GetMapping("{id}")
    public SingleGroupResponseDto getGroup(@NotNull @PathVariable Long id) {
        return groupService.getGroupById(id);

    }
}
