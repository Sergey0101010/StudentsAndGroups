package com.sergey.studentsandgroups.dto;

import com.sergey.studentsandgroups.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleGroupResponseDto {

    private Long groupId;

    private String number;
    private List<GetStudentResponseDto> studentList;


}
