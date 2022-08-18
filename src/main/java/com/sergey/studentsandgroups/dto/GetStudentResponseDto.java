package com.sergey.studentsandgroups.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStudentResponseDto {
    Long studentId;
    String surname;
    LocalDate createAt;
}
