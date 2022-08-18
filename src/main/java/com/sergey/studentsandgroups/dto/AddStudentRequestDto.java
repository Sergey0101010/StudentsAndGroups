package com.sergey.studentsandgroups.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequestDto {

    private Long groupId;

    @NotBlank
    private String surname;
}
