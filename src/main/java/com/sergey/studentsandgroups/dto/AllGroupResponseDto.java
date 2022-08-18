package com.sergey.studentsandgroups.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllGroupResponseDto {

    private String number;
    private int quantity;
    Long id;
}
